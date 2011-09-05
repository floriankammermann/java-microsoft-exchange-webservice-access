/**************************************************************************
 * copyright file="TimeZonePropertyDefinition.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the TimeZonePropertyDefinition.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.util.EnumSet;
import java.util.TimeZone;

import javax.xml.stream.XMLStreamException;

/***
 * Represents a property definition for properties of type TimeZoneInfo.
 * 
 * 
 */
class TimeZonePropertyDefinition extends PropertyDefinition {

	/**
	 * * Initializes a new instance of the TimeZonePropertyDefinition class.
	 * 
	 * @param xmlElementName
	 *            the xml element name
	 * @param uri
	 *            the uri
	 * @param flags
	 *            the flags
	 * @param version
	 *            the version
	 */
	protected TimeZonePropertyDefinition(String xmlElementName, String uri,
			EnumSet<PropertyDefinitionFlags> flags, ExchangeVersion version) {
		super(xmlElementName, uri, flags, version);
	}

	/**
	 * * Loads from XML.
	 * 
	 * @param reader
	 *            the reader
	 * @param propertyBag
	 *            the property bag
	 * @throws Exception
	 *             the exception
	 */
	protected void loadPropertyValueFromXml(EwsServiceXmlReader reader,
			PropertyBag propertyBag) throws Exception {
		TimeZoneDefinition timeZoneDefinition = new TimeZoneDefinition();
		timeZoneDefinition.loadFromXml(reader, this.getXmlElement());
		propertyBag.setObjectFromPropertyDefinition(this, timeZoneDefinition);
	}

	/**
	 * * Writes to XML.
	 * 
	 * @param writer
	 *            the writer
	 * @param propertyBag
	 *            the property bag
	 * @param isUpdateOperation
	 *            the is update operation
	 * @throws ServiceLocalException
	 *             the service local exception
	 * @throws XMLStreamException
	 *             the xML stream exception
	 * @throws ServiceXmlSerializationException
	 *             the service xml serialization exception
	 * @throws Exception
	 *             the exception
	 */
	protected void writePropertyValueToXml(EwsServiceXmlWriter writer,
			PropertyBag propertyBag, boolean isUpdateOperation)
			throws ServiceLocalException, XMLStreamException,
			ServiceXmlSerializationException, Exception {
		TimeZoneDefinition timeZoneDefinition = (TimeZoneDefinition) propertyBag
				.getObjectFromPropertyDefinition(this);

		if (timeZoneDefinition != null) {
			// We emit time zone properties only if we have not emitted the time
			// zone SOAP header
			// or if this time zone is different from that of the service
			// through which the request
			// is being emitted.
			if (!writer.isTimeZoneHeaderEmitted())// || value !=
			// writer.getService().getTimeZone())
			{
				timeZoneDefinition.writeToXml(writer, this.getXmlElement());
			}
		}
	}
	
	/**
	 * Gets the property type.
	 */
	@Override
    public Class getType()
    {
         return TimeZone.class; 
    }
}
