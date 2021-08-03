package com.cutter72.ultrasonicsensor;

import com.cutter72.ultrasonicsensor.sensor.activists.MeasurementsManagerImpl;
import com.cutter72.ultrasonicsensor.sensor.solids.Measurement;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class MeasurementsManagerImplTest {
    private MeasurementsManagerImpl measurements;
    private final int MEASUREMENTS_QUANTITY = 100;
    private List<Measurement> inputMeasurements;

    @Before
    public void setUp() {
        //GIVEN
        measurements = new MeasurementsManagerImpl();
        inputMeasurements = new ArrayList<>();
        int badMeasurements = 0;
        Random random = new Random();
        for (int i = 0; i < MEASUREMENTS_QUANTITY; i++) {
            int dividerOne = random.nextInt(13) + 1;
            if (i % dividerOne == 0) {
                inputMeasurements.add(new Measurement(2 * random.nextDouble() + 88));
                badMeasurements++;
            }
            int dividerTwo = random.nextInt(31) + 1;
            random.nextInt(8);
            if (i % dividerTwo == 0) {
                inputMeasurements.add(new Measurement(0));
                badMeasurements++;
            }
            inputMeasurements.add(new Measurement(0.4 * random.nextDouble() + 91));
        }
        System.out.println("Bad measurements: " + badMeasurements);
        System.out.println(Arrays.toString(inputMeasurements.toArray()));
    }

    @Test
    public void filterByMedian() {
        //GIVEN
        List<Measurement> measurementsToFilter = new ArrayList<>(inputMeasurements);
        //WHEN
        measurements.filterByMedian(measurementsToFilter, 0.4);
        //THEN
        assertEquals(MEASUREMENTS_QUANTITY, measurementsToFilter.size());
    }
}