package com.nilsson.camping.ui.views;

import com.nilsson.camping.app.LanguageManager;
import com.nilsson.camping.model.DailyProfit;
import com.nilsson.camping.service.ProfitsService;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ProfitsView extends VBox {

    private final ProfitsService profitsService = new ProfitsService();
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MMM dd");

    // UI Fields
    private final Label incomeTodayValueLabel = new Label();
    private final Label totalLabelValue = new Label(); // Made field for dynamic update
    private final XYChart.Series<String, Number> profitSeries = new XYChart.Series<>();

    public ProfitsView() {

        // Apply CSS and Layout
        this.getStyleClass().add("content-view");
        this.setPadding(new Insets(20));
        this.setSpacing(15);
        this.setAlignment(Pos.TOP_LEFT);

        Label title = new Label(LanguageManager.getInstance().getString("txt.rentalIncome"));
        title.getStyleClass().add("content-title");

        // Today's Income Display
        HBox incomeTodayBox = createIncomeTodayBox();

        // Total Income Display
        Label totalLabelDesc = new Label(LanguageManager.getInstance().getString("txt.totalRecIncome"));

        // CSS Style
        totalLabelDesc.getStyleClass().add("income-stat-label");
        totalLabelValue.getStyleClass().add("income-stat-value");

        HBox totalIncomeBox = new HBox(10, totalLabelDesc, totalLabelValue);
        totalIncomeBox.setAlignment(Pos.CENTER_LEFT);
        totalIncomeBox.getStyleClass().add("income-stats-box");

        // Create Bar Chart with dynamic Y-axis
        BarChart<String, Number> incomeChart = createIncomeBarChart();
        incomeChart.getData().add(profitSeries);

        // Auto-recalculate on load and populate chart data
        updateView(); // Consolidated update method

        // Live binding for Today's Income
        incomeTodayValueLabel.textProperty().bind(
                Bindings.createStringBinding(() ->
                        String.format("%,.2f SEK", profitsService.getIncomeToday()), profitsService.getObservableDailyProfits()
                )
        );

        // Refresh button
        Button refreshBtn = new Button(LanguageManager.getInstance().getString("btn.refreshProfits"));
        refreshBtn.setOnAction(e -> updateView());
        refreshBtn.getStyleClass().add("action-button");

        // Add all to layout
        this.getChildren().addAll(title, incomeTodayBox, totalIncomeBox, refreshBtn, incomeChart);
    }

    // Logic for updating all stats and the chart data.
    public void updateView() {
        // Recalculate and save the profits based on current rentals
        profitsService.recalculateProfitsFromRentals();

        // Update the total income display
        updateTotalIncomeDisplay();

        // Update the chart data
        updateChartData();
    }

    // Calculates the total sum of all daily profits and updates the label.
    private void updateTotalIncomeDisplay() {
        double totalIncome = profitsService.getDailyProfits().stream()
                .mapToDouble(DailyProfit::getIncome)
                .sum();

        totalLabelValue.setText(String.format("%,.2f SEK", totalIncome));
    }

    private void updateChartData() {
        LocalDate today = LocalDate.now();
        LocalDate fourteenDaysAgo = today.minusDays(14);

        // Filter profits to include only the last 14 days
        List<DailyProfit> recentProfits = profitsService.getDailyProfits().stream()
                .filter(p -> !p.getDate().isBefore(fourteenDaysAgo))
                .filter(p -> !p.getDate().isAfter(today))
                .sorted(Comparator.comparing(DailyProfit::getDate))
                .collect(Collectors.toList());

        ObservableList<XYChart.Data<String, Number>> newData = FXCollections.observableArrayList();
        for (DailyProfit profit : recentProfits) {
            newData.add(new XYChart.Data<>(
                    profit.getDate().format(DATE_FORMATTER),
                    profit.getIncome()));
        }

        // Update the Chart Data Series
        profitSeries.getData().clear();
        profitSeries.getData().setAll(newData);

        // Update and constrain the X-Axis.
        Platform.runLater(() -> {
            CategoryAxis xAxis = (CategoryAxis) profitSeries.getChart().getXAxis();

            xAxis.getCategories().clear();

            // Extract dates from the filtered data
            List<String> categories = newData.stream()
                    .map(XYChart.Data::getXValue)
                    .collect(Collectors.toList());

            xAxis.getCategories().setAll(categories);
        });


        // Dynamic Y-axis setup
        if (!newData.isEmpty() && profitSeries.getChart() != null) {
            double maxIncome = newData.stream()
                    .mapToDouble(data -> data.getYValue().doubleValue())
                    .max()
                    .orElse(0.0);

            // Scale to 120% of max
            double upperBound = Math.ceil(maxIncome * 1.2 / 1000.0) * 1000.0;

            NumberAxis yAxis = (NumberAxis) profitSeries.getChart().getYAxis();

            if (upperBound > 0) {
                yAxis.setUpperBound(upperBound);
                yAxis.setTickUnit(upperBound / 5.0);
                yAxis.setAutoRanging(false);
            } else {
                yAxis.setAutoRanging(true);
            }
        }
    }

    private HBox createIncomeTodayBox() {
        Label incomeTodayLabel = new Label(LanguageManager.getInstance().getString("txt.incomeToday"));
        incomeTodayLabel.getStyleClass().add("income-stat-label");

        incomeTodayValueLabel.getStyleClass().add("income-stat-value");

        HBox box = new HBox(10, incomeTodayLabel, incomeTodayValueLabel);
        box.setAlignment(Pos.CENTER_LEFT);
        box.getStyleClass().add("income-stats-box");

        return box;
    }

    private BarChart<String, Number> createIncomeBarChart() {
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();

        // Enable auto-ranging
        yAxis.setAutoRanging(true);
        yAxis.setForceZeroInRange(true);

        final BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);

        barChart.setTitle(LanguageManager.getInstance().getString("txt.dailyIncome"));
        xAxis.setLabel(LanguageManager.getInstance().getString("x.date"));
        yAxis.setLabel(LanguageManager.getInstance().getString("y.income"));
        barChart.setLegendVisible(false);

        barChart.getStyleClass().add("profit-chart");

        return barChart;
    }
}