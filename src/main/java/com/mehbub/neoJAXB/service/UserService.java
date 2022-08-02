package com.mehbub.neoJAXB.service;

import com.mehbub.neoJAXB.dto.User;
import com.mehbub.neoJAXB.enumeration.ResponseStatus;
import com.mehbub.neoJAXB.util.XMLUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserService {
    private JAXBContext context;

    @PostConstruct
    private void init() throws JAXBException {
        context = JAXBContext.newInstance(User.class);
    }

    //region public methods
    public AbstractMap.SimpleEntry<Integer, List<User>> saveUsersInXML(User[] userObject) {
        if (userObject == null) {
            return new AbstractMap.SimpleEntry<>(ResponseStatus.ERROR.getValue(), null);
        }

        List<User> userList = Arrays.asList(userObject);
        try {
            for (int index = 0; index < userList.size(); index++) {
                createUserXML(userList.get(index));
            }
            return new AbstractMap.SimpleEntry<>(ResponseStatus.SUCCESS.getValue(), userList);
        } catch (JAXBException e) {
            return new AbstractMap.SimpleEntry<>(ResponseStatus.EXCEPTION.getValue(), null);
        }


        ////throw new RuntimeException("Can't create or update user xml");
    }

    public AbstractMap.SimpleEntry<Integer, List<User>> saveUserInXML(User user) {
        if (user == null) {
            return new AbstractMap.SimpleEntry<>(ResponseStatus.ERROR.getValue(), null);
        }
        try {
            createUserXML(user);
            return new AbstractMap.SimpleEntry<>(ResponseStatus.SUCCESS.getValue(), List.of(user));
        } catch (JAXBException e) {
            return new AbstractMap.SimpleEntry<>(ResponseStatus.EXCEPTION.getValue(), null);
        }
    }

    public AbstractMap.SimpleEntry<Integer, List<User>> getUserByIdFromXML(Integer id) {
        try {
            Path path = XMLUtil.getXMLFilePathByID(id);
            Boolean isExists = Files.exists(path);
            if(!isExists){
                return new AbstractMap.SimpleEntry<>(ResponseStatus.VALIDATION.getValue(), null);
            }
            User unmarshalledUser = (User) context.createUnmarshaller().unmarshal(path.toFile());
            return new AbstractMap.SimpleEntry<>(ResponseStatus.SUCCESS.getValue(), List.of(unmarshalledUser));
        } catch (JAXBException e) {
            return new AbstractMap.SimpleEntry<>(ResponseStatus.EXCEPTION.getValue(), null);
        }
    }

    public AbstractMap.SimpleEntry<Integer, List<User>> getUsersFromXML() {
        List<User> unmarshalledUserList = new ArrayList<>();
        try {
            List<File> files = XMLUtil.getXMLFilesFromLocation();

            if (files == null) {
                return new AbstractMap.SimpleEntry<>(ResponseStatus.VALIDATION.getValue(), unmarshalledUserList);
            }

            if (files.size() == 0) {
                return new AbstractMap.SimpleEntry<>(ResponseStatus.VALIDATION.getValue(), unmarshalledUserList);
            }

            for (int index = 0; index < files.size(); index++) {
                File unmarshalledFile = files.get(index);
                User unmarshalledUser = (User) context.createUnmarshaller().unmarshal(unmarshalledFile);
                unmarshalledUserList.add(unmarshalledUser);
            }
            return new AbstractMap.SimpleEntry<>(ResponseStatus.SUCCESS.getValue(), unmarshalledUserList);
        } catch (JAXBException e) {
            return new AbstractMap.SimpleEntry<>(ResponseStatus.EXCEPTION.getValue(), null);
        }
    }

    //endregion

    //region private methods

    /**
     * This method allows to create xml from our java object "User" and save it by the given path.
     *
     * @param anUser
     */
    private void createUserXML(User anUser) throws JAXBException {
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String fileName = String.valueOf(anUser.getId()).concat(".xml");
        File file = XMLUtil.getXMLFile(fileName);
        marshaller.marshal(anUser, file);
    }

    //endregion

}
