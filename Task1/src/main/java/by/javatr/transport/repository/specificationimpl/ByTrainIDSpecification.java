package by.javatr.transport.repository.specificationimpl;

import by.javatr.transport.Sorter.FactorySorter;
import by.javatr.transport.Sorter.TrainPassengerSorter;
import by.javatr.transport.entity.TrainPassenger;
import by.javatr.transport.repository.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ByTrainIDSpecification implements Specification<TrainPassenger> {

    private UUID searchID;

    private FactorySorter factorySorter = FactorySorter.getInstance();
    private TrainPassengerSorter trainPassengerSorter = factorySorter.getTrainPassengerSorter();

    public ByTrainIDSpecification(UUID searchID) {
        this.searchID = searchID;
    }

    public boolean match(TrainPassenger bean) {
        return this.searchID.equals(bean.getId());
    }


}
