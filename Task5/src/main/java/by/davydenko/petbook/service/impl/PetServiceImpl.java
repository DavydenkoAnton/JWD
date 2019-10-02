package by.davydenko.petbook.service.impl;

import by.davydenko.petbook.dao.DaoException;
import by.davydenko.petbook.dao.DaoFactory;
import by.davydenko.petbook.dao.PetDao;
import by.davydenko.petbook.entity.PetType;
import by.davydenko.petbook.entity.Pet;
import by.davydenko.petbook.service.PetService;
import by.davydenko.petbook.service.ServiceException;
import by.davydenko.petbook.service.util.creator.CreatorException;
import by.davydenko.petbook.service.util.creator.CreatorFactory;
import by.davydenko.petbook.service.util.creator.PetCreator;
import by.davydenko.petbook.service.util.creator.UserCreator;

import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public final class PetServiceImpl implements PetService {

    private static final String PET_AVATAR_FOLDER = "img/pets_avatars";
    private static final String PET_PHOTO_FOLDER = "img/pets_photo";
    private static final String JPEG_FORMAT = "image/jpeg";
    private static final int PHOTOS_MAX_SIZE = 5;
    private UserCreator userCreator;
    private PetCreator petCreator;
    private PetDao petDao;

    public PetServiceImpl() {
        CreatorFactory creatorFactory = CreatorFactory.getInstance();
        DaoFactory daoFactory = DaoFactory.getInstance();
        userCreator = creatorFactory.getUserCreator();
        petCreator = creatorFactory.getPetCreator();
        petDao = daoFactory.getPetDao();
    }

    @Override
    public Optional<List<Pet>> getAllPets() throws ServiceException {
        Optional<List<Pet>> optionalPets;
        try {
            optionalPets = petDao.read();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return optionalPets;
    }

    @Override
    public Optional<List<Pet>> getAllPetsNoUser(int id) throws ServiceException {
        Optional<List<Pet>> optionalPets;
        try {
            optionalPets = petDao.readAllNoUser(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return optionalPets;
    }

    @Override
    public Optional<List<Pet>> getByTypeNoUser(String petType, int id) throws ServiceException {
        Optional<List<Pet>> optionalPets;
        try {
            optionalPets = petDao.readByTypeNoUser(petType, id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return optionalPets;
    }

    @Override
    public Optional<List<Pet>> getByType(String type) throws ServiceException {
        Optional<List<Pet>> optionalPets;
        PetType petType;
        try {
            petType = petCreator.createType(type);
        } catch (CreatorException e) {
            throw new ServiceException(e);
        }
        if (petType != null) {
            try {
                optionalPets = petDao.readByType(petType);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        } else {
            try {
                optionalPets = petDao.read();
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return optionalPets;
    }

    @Override
    public int getDogPercent() throws ServiceException {
        return getPercentOfType(PetType.DOG);
    }

    @Override
    public int getCatPercent() throws ServiceException {
        return getPercentOfType(PetType.CAT);
    }

    @Override
    public int getBirdPercent() throws ServiceException {
        return getPercentOfType(PetType.BIRD);
    }

    @Override
    public int getOtherPercent() throws ServiceException {
        return getPercentOfType(PetType.OTHER);
    }

    private int getPercentOfType(PetType type) throws ServiceException {
        Optional<List<Pet>> optionalPetsByType;
        Optional<List<Pet>> optionalPets;
        int result = 0;
        try {
            optionalPetsByType = petDao.readByType(type);
            optionalPets = petDao.read();
            if (optionalPets.isPresent() && optionalPetsByType.isPresent()) {
                List<Pet> petsByType = optionalPetsByType.get();
                List<Pet> petsAll = optionalPets.get();
                if (petsByType.size() > 0 && petsAll.size() > 0) {
                    double byTypePets = petsByType.size();
                    double allPets = petsAll.size();
                    double buffResult = 100 / (allPets / byTypePets);
                    result = (int) buffResult;
                }
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public void registerByUserId(int userId) throws ServiceException {
        try {
            petDao.createByUserId(userId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<Pet> getByUserId(String userId) throws ServiceException {
        Optional<Pet> pet;
        try {
            int id = petCreator.userId(userId);
            pet = petDao.readByUserId(id);
        } catch (CreatorException | DaoException e) {
            throw new ServiceException(e);
        }
        return pet;
    }

    @Override
    public Optional<List<Pet>> getMessageSenders(String id) throws ServiceException {
        Optional<List<Pet>> optionalPets;
        int userId;
        try {
            userId = userCreator.createId(id);
        } catch (CreatorException e) {
            throw new ServiceException(e);
        }

        try {
            optionalPets = petDao.readCorrespondents(userId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

        return optionalPets;
    }

    @Override
    public Optional<Pet> getSender(String userId) throws ServiceException {
        Optional<Pet> correspondent = null;
//        try {
//            int correspondentId = petCreator.createCorrespondentId(request);
//            correspondent = petDao.readByUserId(correspondentId);
//        } catch (CreatorException | DaoException e) {
//            throw new ServiceException(e);
//        }
        return correspondent;
    }

    @Override
    public Optional<Pet> getReceiver(String userId) throws ServiceException {
        Optional<Pet> reciever = null;
//        try {
//            int recieverId = petCreator.createReceiverId(request);
//            reciever = petDao.readByUserId(recieverId);
//        } catch (CreatorException | DaoException e) {
//            throw new ServiceException(e);
//        }
        return reciever;
    }

    @Override
    public List<String> getPetPhotosById(int id, int from) throws ServiceException {
        List<String> photoUrls;
        int to = from + PHOTOS_MAX_SIZE;
        try {
            photoUrls = petDao.readPhotosUrl(id, from, to);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return photoUrls;
    }

    @Override
    public List<String> getPetPhotosById(int id) throws ServiceException {
        List<String> photoUrls;
        try {
            photoUrls = petDao.readPhotosUrl(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return photoUrls;
    }

    @Override
    public List<String> getPagingPhotoCount(List<String> petPhotoUrl, int from) {
        List<String> bufPhotosUrl = new ArrayList<>();
        int count = 0;
        int to = from + PHOTOS_MAX_SIZE;
        for (String s : petPhotoUrl) {
            count++;
            if (s != null && count < to && count > from) {
                bufPhotosUrl.add(s);
            }

        }
        return bufPhotosUrl;
    }

    @Override
    public void uploadAvatar(Part image, String path, String userId) throws ServiceException {
        uploadImage(image, path, userId, PET_AVATAR_FOLDER);
    }

    @Override
    public void uploadPhoto(Part image, String path, String userId) throws ServiceException {
        uploadImage(image, path, userId, PET_PHOTO_FOLDER);
    }

    private void uploadImage(Part image, String path, String userId, String pathFolder) throws ServiceException {
        int id = 0;
        String imageName;
        Path outputFile;
        try {
            id = petCreator.userId(userId);
            imageName = petCreator.imageName(image, id);
            path += pathFolder;
            outputFile = petCreator.outputFile(path, imageName);
        } catch (CreatorException e) {
            throw new ServiceException(e);
        }
        path = path.replace("\\\\", File.separator);
        Path dirPathObj = Paths.get(path);
        if (!Files.exists(dirPathObj)) {
            try {
                Files.createDirectories(dirPathObj);
            } catch (IOException e) {
                throw new ServiceException("Problem Occurred While Creating The Directory Structure" + e);
            }
        }
        try (final ReadableByteChannel input = Channels.newChannel(image.getInputStream());
             final WritableByteChannel output = Channels.newChannel(new FileOutputStream(outputFile.toFile()));) {
            pipe(input, output);
        } catch (IOException e) {
            throw new ServiceException("IO error during read/write user avatar", e);
        }
        path = pathFolder + "/" + imageName;
        switch (pathFolder) {
            case PET_AVATAR_FOLDER:
                uploadAvatar(id, path);
                break;
            case PET_PHOTO_FOLDER:
                uploadPhoto(id, path);
                break;
        }

    }

    private void uploadPhoto(int id, String path) throws ServiceException {
        try {
            petDao.createPhotoById(id, path);
        } catch (DaoException e) {
            throw new ServiceException("cannot write image url to database");
        }
    }

    private void uploadAvatar(int id, String path) throws ServiceException {
        try {
            petDao.createAvatarById(id, path);
        } catch (DaoException e) {
            throw new ServiceException("cannot write image url to database");
        }
    }

    private void pipe(ReadableByteChannel in, WritableByteChannel out)
            throws IOException {
        final ByteBuffer buffer = ByteBuffer.allocate(1024);
        while (in.read(buffer) >= 0 || buffer.position() > 0) {
            buffer.flip();
            out.write(buffer);
            buffer.compact();
        }
    }

    @Override
    public void uploadName(String petName, String userId) throws ServiceException {
        String name;
        int id;
        try {
            name = userCreator.createName(petName);
            id = userCreator.createId(userId);
        } catch (CreatorException e) {
            throw new ServiceException(e);
        }

        try {
            petDao.updateName(id, name);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void uploadBreed(String petBreed, String userId) throws ServiceException {
        String breed;
        int id;
        try {
            breed = petCreator.createBreed(petBreed);
            id = userCreator.createId(userId);
        } catch (CreatorException e) {
            throw new ServiceException(e);
        }

        try {
            petDao.updateBreed(id, breed);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void uploadAge(String petAge, String userId) throws ServiceException {
        int age;
        int id;
        try {
            age = petCreator.createAge(petAge);
            id = userCreator.createId(userId);
        } catch (CreatorException e) {
            throw new ServiceException(e);
        }

        try {
            petDao.updateAge(id, age);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void uploadType(String petType, String userId) throws ServiceException {
        PetType type;
        int id;
        try {
            type = petCreator.createType(petType);
            id = userCreator.createId(userId);
        } catch (CreatorException e) {
            throw new ServiceException(e);
        }

        try {
            petDao.updateType(id, type);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
