package by.davydenko.petbook.controller;

import by.davydenko.petbook.controller.command.Command;
import by.davydenko.petbook.controller.command.impl.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

final class CommandProvider {

    private static final Logger logger = LogManager.getLogger(CommandProvider.class);

    private final static CommandProvider instance = new CommandProvider();
    private final Map<String, Command> commands = new ConcurrentHashMap<>();

    private CommandProvider() {
        commands.put("main", new MainPageCommand());
        commands.put("articles", new ArticlesPageCommand());
        commands.put("article", new ArticlePageCommand());
        commands.put("addPetPhoto", new AddPetPhotoCommand());
        commands.put("addArticlePage", new AddArticlePageCommand());
        commands.put("addArticle", new AddArticle());
        commands.put("addArticleCommand", new AddArticleCommand());
        commands.put("deletePetPhotos", new DeletePetPhotos());
        commands.put("pagingFirstAdminUsers", new PagingFirstAdminUsers());
        commands.put("pagingNextAdminUsers", new PagingNextAdminUsers());
        commands.put("pagingPrevAdminUsers", new PagingPrevAdminUsers());
        commands.put("settings", new AdminSettingsPage());
        commands.put("editArticlePage", new EditArticlePageCommand());
        commands.put("editArticle", new EditArticle());
        commands.put("editArticleCommand", new EditArticleCommand());
        commands.put("deleteArticle", new DeleteArticleCommand());
        commands.put("adminArticles", new AdminArticlesPageCommand());
        commands.put("changeUserRole", new ChangeUserRoleCommand());
        commands.put("locale", new ChangeLocaleCommand());
        commands.put("login", new LoginPageCommand());
        commands.put("photos", new PhotosPageCommand());
        commands.put("visit", new VisitPageCommand());
        commands.put("loginUser", new LoginUserCommand());
        commands.put("deleteByLogin", new DeleteUserCommand());
        commands.put("user", new UserPageCommand());
        commands.put("pet", new PetPageCommand());
        commands.put("pets", new PetsPageCommand());
        commands.put("getAllPets", new GetAllPetsCommand());
        commands.put("getPetsByType", new GetPetsByTypeCommand());
        commands.put("logout", new LogoutUserCommand());
        commands.put("registration", new RegisterPageCommand());
        commands.put("registerUser", new RegisterUserCommand());
        commands.put("admin", new UserPageCommand());
        commands.put("users", new AdminUsersPageCommand());
        commands.put("messages", new MessagePageCommand());
        commands.put("sendMessage", new SendMessageCommand());
        commands.put("sendMessagePage", new SendMessagePageCommand());
        commands.put("profile", new ProfilePageCommand());
        commands.put("editAdminLogin", new EditAdminLogin());
        commands.put("editAdminPassword", new EditAdminPassword());
        commands.put("editUserAvatar", new EditUserAvatarCommand());
        commands.put("editUserName", new EditUserNameCommand());
        commands.put("editPetAvatar", new EditPetAvatarCommand());
        commands.put("editPetName", new EditPetNameCommand());
        commands.put("editPetBreed", new EditPetBreedCommand());
        commands.put("editPetType", new EditPetTypeCommand());
        commands.put("editPetAge", new EditPetAgeCommand());
        commands.put("pagingPetPhotoPrev", new PagingPetPhotoPrev());
        commands.put("pagingPetPhotoNext", new PagingPetPhotoNext());
        commands.put("getChatMessages", new GetChatMessages());
    }

    public static CommandProvider getInstance() {
        return instance;
    }


    public Command getCommand(String commandName) {

        Command command = null;

        try {
            command = commands.get(commandName);
        } catch (NullPointerException e) {
            logger.error("NullPointerException in command");
        } catch (IllegalArgumentException e) {
            logger.error("IllegalArgumentException in command");
        }

        return command;
    }


}
