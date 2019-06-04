package by.javatr.transport.repository.specificationimpl;


import by.javatr.transport.entity.TrainPassenger;
import by.javatr.transport.repository.Specification;

import java.util.UUID;

public class ByTrainIDSpecification implements Specification<TrainPassenger> {

    private UUID searchID;



    public ByTrainIDSpecification(UUID searchID) {
        this.searchID = searchID;
    }

    public boolean match(TrainPassenger bean) {
        return this.searchID.equals(bean.getId());
    }


}
