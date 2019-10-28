package by.davydenko.petbook.service.impl;

import by.davydenko.petbook.dao.DaoException;
import by.davydenko.petbook.dao.DaoFactory;
import by.davydenko.petbook.dao.PetDao;
import by.davydenko.petbook.dao.pool.ConnectionPool;
import by.davydenko.petbook.dao.pool.ConnectionPoolException;
import by.davydenko.petbook.entity.Pet;
import by.davydenko.petbook.entity.PetType;
import org.junit.jupiter.api.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
 class PetServiceImplTest {

    private static   PetDao petDao;

    @BeforeAll
     static void init() throws ConnectionPoolException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        connectionPool.init();
        DaoFactory daoFactory = DaoFactory.getInstance();
        petDao = daoFactory.getPetDao();
    }

    @Test
    @Order(1)
    @DisplayName("create pet by user id")
     void registerByUserId() throws DaoException {
        int id = 47;
        petDao.createByUserId(id);
        Optional<Pet> optionalPet = petDao.readByUserId(id);
        assertNotEquals(Optional.empty(), optionalPet);
    }

    @Test
    @Order(2)
    @DisplayName("create pet by wrong user id")
    public void registerByWrongUserId() {
        int id = -1;
        assertThrows(DaoException.class, () -> petDao.createByUserId(id));
    }

    @Test
    @Order(3)
    @DisplayName("get pet by user id")
    public void getByUserId() throws DaoException {
        int id = 47;
        Optional<Pet> optionalPet = petDao.readByUserId(id);
        assertNotEquals(Optional.empty(), optionalPet);
    }

    @Test
    @Order(4)
    @DisplayName("get pet by wrong user id")
    public void getByWrongUserId() throws DaoException {
        int wrongId = -1;
        Optional<Pet> optionalPet = petDao.readByUserId(wrongId);
        assertEquals(Optional.empty(), optionalPet);
    }


    @Test
    @Order(5)
    @DisplayName("upload pet name")
    public void uploadName() throws DaoException {
        int id = 47;
        String petName = "testPetName";
        petDao.updateName(id, petName);
        Optional<Pet> optionalPet = petDao.readByUserId(id);
        if (optionalPet.isPresent()) {
            Pet pet = optionalPet.get();
            assertEquals(pet.getName(), petName);
        }
    }

    @Test
    @Order(6)
    @DisplayName("upload pet age")
    public void uploadAge() throws DaoException {
        int id = 47;
        int petAge = 1;
        petDao.updateAge(id, petAge);
        Optional<Pet> optionalPet = petDao.readByUserId(id);
        if (optionalPet.isPresent()) {
            Pet pet = optionalPet.get();
            assertEquals(pet.getAge(), petAge);
        }
    }

    @Test
    @Order(7)
    @DisplayName("upload pet breed")
    public void uploadBreed() throws DaoException {
        int id = 47;
        String petBreed = "testPetBreed";
        petDao.updateBreed(id, petBreed);
        Optional<Pet> optionalPet = petDao.readByUserId(id);
        if (optionalPet.isPresent()) {
            Pet pet = optionalPet.get();
            assertEquals(pet.getBreed(), petBreed);
        }
    }

    @Test
    @Order(8)
    @DisplayName("upload pet avatar url")
    public void uploadAvatarUrl() throws DaoException {
        int id = 47;
        String avatarUrl = "testAvatarUrl";
        petDao.updateAvatarURL(id, avatarUrl);
        Optional<Pet> optionalPet = petDao.readByUserId(id);
        if (optionalPet.isPresent()) {
            Pet pet = optionalPet.get();
            assertEquals(pet.getAvatarUrl(), avatarUrl);
        }
    }

    @Test
    @Order(9)
    @DisplayName("upload pet type")
    public void uploadType() throws DaoException {
        int id = 47;
        PetType petType=PetType.OTHER;
        petDao.updateType(id, petType);
        Optional<Pet> optionalPet = petDao.readByUserId(id);
        if (optionalPet.isPresent()) {
            Pet pet = optionalPet.get();
            assertEquals(pet.getType(), petType);
        }
    }

    @Test
    @Order(10)
    @DisplayName("delete pet by user id")
    public void deleteByUserId() throws DaoException {
        int userId = 47;
        Optional<Pet> optionalPet=petDao.readByUserId(userId);
        if(optionalPet.isPresent()){
            petDao.delete(userId);
            optionalPet=petDao.readByUserId(userId);
            assertEquals(Optional.empty(),optionalPet);
        }else{
            fail("[ cannot find pet by user id ]");
        }

    }
}
