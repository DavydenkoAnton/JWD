package by.davydenko.petbook.dao;

import by.davydenko.petbook.entity.Pet;

public interface PetDao extends Dao<Pet> {
    @Override
    Integer create(Pet entity);

    @Override
    Pet read(Integer identity);

    @Override
    void update(Pet entity);

    @Override
    void delete(Integer identity);
}
