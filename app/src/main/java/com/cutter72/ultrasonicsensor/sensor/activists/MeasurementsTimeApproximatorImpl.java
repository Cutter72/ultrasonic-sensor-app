package com.cutter72.ultrasonicsensor.sensor.activists;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;

import com.cutter72.ultrasonicsensor.sensor.solids.Measurement;

import java.util.Date;
import java.util.List;

public class MeasurementsTimeApproximatorImpl implements MeasurementsTimeApproximator {

    @Override
    public void approximate(@NonNull List<Measurement> measurements, @IntRange(from = 0) int timeSpanToDistribute) {
        int measurementsSize = measurements.size();
        if (measurementsSize > 1) {
            final double TIME_DELTA = timeSpanToDistribute / (measurementsSize - 1.0);
            long firstMeasurementTimestamp = measurements.get(0).getDate().getTime();
            Measurement lastMeasurement = measurements.get(measurementsSize - 1);
            long lastMeasurementsTimestamp = firstMeasurementTimestamp + timeSpanToDistribute;
            lastMeasurement.setDate(new Date(lastMeasurementsTimestamp));
            for (int i = 0; i < measurementsSize - 1; i++) {
                Measurement measurement = measurements.get(i);
                long newTimeStamp = (long) Math.round(firstMeasurementTimestamp + i * TIME_DELTA);
                measurement.setDate(new Date(newTimeStamp));
            }
        }
    }
}
