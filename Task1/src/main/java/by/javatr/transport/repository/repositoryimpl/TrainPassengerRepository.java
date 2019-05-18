package by.javatr.transport.repository.repositoryimpl;

import by.javatr.transport.Sorter.FactorySorter;
import by.javatr.transport.Sorter.TrainPassengerSorter;
import by.javatr.transport.dao.TrainPassengerDAO;
import by.javatr.transport.dao.DAOFactory;
import by.javatr.transport.entity.TrainPassenger;
import by.javatr.transport.exception.DaoException;
import by.javatr.transport.exception.RepositoryException;
import by.javatr.transport.exception.TxtTrainPassengerDAOExeption;
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

    }

    public static TrainPassengerRepository getInstance() {
        if (instance == null) {
            instance = new TrainPassengerRepository();
        }
        return instance;
    }


    public void addTrainPassenger(Specification<TrainPassenger> spec, TrainPassenger trainPassenger) throws DaoException {
        boolean notContain = true;

        for (TrainPassenger trainPassengerIter : trainsPassenger) {
            if (spec.match(trainPassengerIter)) {
                notContain = false;
            }
        }

        if (notContain) {
            trainsPassenger.add(trainPassenger);
            trainPassengerDAO.update(trainsPassenger);
        }

    }

    public List<TrainPassenger> sort(Specification<TrainPassenger> spec) {

        List<TrainPassenger> result = trainsPassenger;
        result.sort(TrainPassenger.COMPARE_BY_ID);
        return result;
    }


    public String getAll() throws RepositoryException, TxtTrainPassengerDAOExeption {
        trainsPassenger = trainPassengerDAO.read();
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
