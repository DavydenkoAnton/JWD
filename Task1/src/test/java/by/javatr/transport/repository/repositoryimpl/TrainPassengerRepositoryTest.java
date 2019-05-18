package by.javatr.transport.repository.repositoryimpl;

import by.javatr.transport.entity.TrainPassenger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TrainPassengerRepositoryTest {

    List<TrainPassenger> trainPassengers;

    @Before
    public void intialise() {
        trainPassengers = new ArrayList<>();
    }

    @Test
    public void getAll() {
        int expectedSize = 0;
        int result = trainPassengers.size();
        Assert.assertEquals(expectedSize,result);
    }
}
