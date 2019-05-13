package by.javatr.transport.repository.repositoryimpl;

import by.javatr.transport.dao.TrainPassengerDAO;
import by.javatr.transport.dao.factory.DAOFactory;
import by.javatr.transport.entity.TrainPassenger;
import by.javatr.transport.exception.DaoException;
import by.javatr.transport.exception.RepositoryException;
import by.javatr.transport.repository.Repository;
import by.javatr.transport.repository.Specification;

import java.util.ArrayList;
import java.util.List;

public class TrainPassengerRepository implements Repository<TrainPassenger> {

    private List<TrainPassenger> trainsPassenger = new ArrayList<>();
    private static TrainPassengerRepository instance;
    private DAOFactory daoFactory = DAOFactory.getInstance();
    private TrainPassengerDAO trainPassengerDAO = daoFactory.getTrainPassengerDAO();

    private TrainPassengerRepository() {
        trainsPassenger = trainPassengerDAO.read();
    }

    public static TrainPassengerRepository getInstance() {
        if (instance == null) {
            instance = new TrainPassengerRepository();
        }
        return instance;
    }


    public void addTrainPassenger(String request) throws DaoException {
        trainPassengerDAO.addTrainPassenger(request);
    }


    public String getAll() throws RepositoryException {
        if (trainsPassenger.size() == 0) throw new RepositoryException("no trains passenger in data base");
        String trainPassenger = "";
        for (TrainPassenger train : this.trainsPassenger) {
            trainPassenger += train + "\n";
        }
        return trainPassenger;
    }

    public List<TrainPassenger> find(Specification<TrainPassenger> spec) {

        List<TrainPassenger> result = new ArrayList<>();

        for (TrainPassenger trainPassenger : trainsPassenger) {
            if (spec.match(trainPassenger)) {
                result.add(trainPassenger);
            }
        }
        return result;
    }
}
