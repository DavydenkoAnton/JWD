package by.davydenko.petbook.controller.command.impl;

import by.davydenko.petbook.controller.command.util.Attribute;
import by.davydenko.petbook.entity.Pet;
import by.davydenko.petbook.service.PetService;
import by.davydenko.petbook.service.ServiceException;
import by.davydenko.petbook.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class GetAllPetsCommand implements by.davydenko.petbook.controller.command.Command {
    private static final Logger logger = LogManager.getLogger(GetAllPetsCommand.class);
    private static final String PETS_PAGE_URL = "http://localhost:8080/pb/pets.html";
    PetService petService;

    public GetAllPetsCommand(){
        ServiceFactory serviceFactory=ServiceFactory.getInstance();
        petService=serviceFactory.getPetService();
    }
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        Optional<List<Pet>> optionalPets;
        try{
            optionalPets=petService.getAllPets();
            if(optionalPets.isPresent()){
                List<Pet> pets=optionalPets.get();
                request.getSession().setAttribute(Attribute.PETS,pets);
            }
        }catch (ServiceException e){
            logger.error(e);
        }
        redirectToPetsPage(response);
    }

    private void redirectToPetsPage(HttpServletResponse response) {
        response.setContentType("pets.jsp");
        try {
            response.sendRedirect(PETS_PAGE_URL);
        } catch (IOException e) {
            logger.error("IOException (not redirected)", e);
        }
    }

}
