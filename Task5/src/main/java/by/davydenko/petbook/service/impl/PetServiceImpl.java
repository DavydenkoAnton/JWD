package by.davydenko.petbook.service.impl;

import by.davydenko.petbook.controller.command.util.Attribute;
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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;


public final class PetServiceImpl implements PetService {

    private static final String PET_AVATAR_FOLDER = "img/pets_avatars";
    private static final String JPEG_FORMAT = "image/jpeg";
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
            optionalPets=petDao.read();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return optionalPets;
    }

    @Override
    public Optional<List<Pet>> getPetsByType(HttpServletRequest request) throws ServiceException {
        Optional<List<Pet>> optionalPets;
        PetType petType;
        try {
            petType = petCreator.createType(request);
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
    public int getDogPrefer() throws ServiceException {
        return getPrefer(PetType.DOG);
    }

    @Override
    public int getCatPrefer() throws ServiceException {
        return getPrefer(PetType.CAT);
    }

    @Override
    public int getBirdPrefer() throws ServiceException {
        return getPrefer(PetType.BIRD);
    }

    @Override
    public int getOtherPrefer() throws ServiceException {
        return getPrefer(PetType.OTHERS);
    }

    private int getPrefer(PetType type) throws ServiceException {
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
    public void registerPet(HttpServletRequest request) throws ServiceException {
        try {
            int userId = userCreator.createId(request);
            petDao.createByUserId(userId);
        } catch (CreatorException | DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<Pet> getPetByUserId(HttpServletRequest request) throws ServiceException {
        Optional<Pet> pet;
        try {
            int id = petCreator.idByUserId(request);
            pet = petDao.readByUserId(id);
        } catch (CreatorException | DaoException e) {
            throw new ServiceException(e);
        }
        return pet;
    }

    @Override
    public Optional<Pet> getPetById(HttpServletRequest request) throws ServiceException {
        Optional<Pet> pet;
        try {
            int id = petCreator.byId(request);
            pet = petDao.readByUserId(id);
        } catch (CreatorException | DaoException e) {
            throw new ServiceException(e);
        }
        return pet;
    }

    @Override
    public Optional<List<Pet>> getMessageSenders(HttpServletRequest request) throws ServiceException {
        Optional<List<Pet>> optionalPets;
        int userId;
        try {
            userId = userCreator.createId(request);
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
    public Optional<Pet> getSender(HttpServletRequest request) throws ServiceException {
        Optional<Pet> correspondent;
        try {
            int correspondentId = petCreator.createCorrespondentId(request);
            correspondent = petDao.readByUserId(correspondentId);
        } catch (CreatorException | DaoException e) {
            throw new ServiceException(e);
        }
        return correspondent;
    }

    @Override
    public Optional<Pet> getReceiver(HttpServletRequest request) throws ServiceException {
        Optional<Pet> reciever;
        try {
            int recieverId = petCreator.createReceiverId(request);
            reciever = petDao.readByUserId(recieverId);
        } catch (CreatorException | DaoException e) {
            throw new ServiceException(e);
        }
        return reciever;
    }

    @Override
    public void uploadAvatar(HttpServletRequest request) throws ServiceException {

        Part image;
        try {
            image = request.getPart(Attribute.PET_AVATAR);
        } catch (IOException | ServletException e) {
            throw new ServiceException("cannot get image from request", e);
        }


        int id = 0;
        try {
            id = userCreator.createId(request);
        } catch (CreatorException e) {
            throw new ServiceException("cannot get user id", e);
        }

        String imageName;

        String contentType = image.getContentType();
        if (contentType.equalsIgnoreCase(JPEG_FORMAT)) {
            imageName = id + ".jpg";
        } else {
            throw new ServiceException("wrong image format for pet avatar");
        }

        final String path = request.getServletContext().getRealPath("/") + PET_AVATAR_FOLDER;
        final Path outputFile;
        try {
            outputFile = Paths.get(path, imageName);
        } catch (InvalidPathException e) {
            throw new ServiceException("Invalid path for pet avatar", e);
        }

        try (final ReadableByteChannel input = Channels.newChannel(image.getInputStream());
             final WritableByteChannel output = Channels.newChannel(new FileOutputStream(outputFile.toFile()));) {
            pipe(input, output);
        } catch (IOException e) {
            throw new ServiceException("IO error during read/write user avatar", e);
        }
        setAvatarURL(id, imageName);
    }

    private void setAvatarURL(int userId, String imageName) throws ServiceException {
        final String path = PET_AVATAR_FOLDER + "/" + imageName;
        try {
            petDao.updateAvatarURL(userId, path);
        } catch (DaoException e) {
            throw new ServiceException("cannot write avatar url to db");
        }
    }

    private static void pipe(ReadableByteChannel in, WritableByteChannel out)
            throws IOException {
        final ByteBuffer buffer = ByteBuffer.allocate(1024);
        while (in.read(buffer) >= 0 || buffer.position() > 0) {
            buffer.flip();
            out.write(buffer);
            buffer.compact();
        }
    }

    @Override
    public void uploadName(HttpServletRequest request) throws ServiceException {
        String name;
        int id;
        try {
            name = userCreator.createName(request);
            id = userCreator.createId(request);
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
    public void uploadBreed(HttpServletRequest request) throws ServiceException {
        String breed;
        int id;
        try {
            breed = petCreator.createBreed(request);
            id = userCreator.createId(request);
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
    public void uploadAge(HttpServletRequest request) throws ServiceException {
        int age;
        int id;
        try {
            age = petCreator.createAge(request);
            id = userCreator.createId(request);
        } catch (CreatorException e) {
            throw new ServiceException(e);
        }

        try {
            petDao.updateAge(id, age);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }


}
