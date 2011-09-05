
package com.microsoft.schemas.exchange.services._2006.types;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfMailboxData complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfMailboxData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MailboxData" type="{http://schemas.microsoft.com/exchange/services/2006/types}MailboxData" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfMailboxData", propOrder = {
    "mailboxData"
})
public class ArrayOfMailboxData {

    @XmlElement(name = "MailboxData", nillable = true)
    protected List<MailboxData> mailboxData;

    /**
     * Gets the value of the mailboxData property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the mailboxData property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMailboxData().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MailboxData }
     * 
     * 
     */
    public List<MailboxData> getMailboxData() {
        if (mailboxData == null) {
            mailboxData = new ArrayList<MailboxData>();
        }
        return this.mailboxData;
    }

}
