package com.mehbub.neoJAXB.dto;

import lombok.*;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@XmlRootElement(name = "user") // the name of the root XML element is derived from the class name and we can also specify the name of the root element of the XML using its name attribute.
@XmlType(propOrder = { "id", "name", "username", "email", "phone","website","address","company"  }) // define the order in which the fields are written in the XML file.
public class User {
    private int id;
    private String name;
    // @XmlElement(name = "username") // define the actual XML element name which will be used
    private String username;
    private String email;
    @XmlTransient // annotate fields that we don't want to be included in XML.
    private Address address;
    private String phone;
    private String website;
    @XmlTransient // annotate fields that we don't want to be included in XML.
    private Company company;
}



