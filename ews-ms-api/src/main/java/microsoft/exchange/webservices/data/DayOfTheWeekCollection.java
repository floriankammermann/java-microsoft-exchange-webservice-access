/**************************************************************************
 * copyright file="DayOfTheWeekCollection.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the DayOfTheWeekCollection.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.stream.XMLStreamException;

/**
 * Represents a collection of DayOfTheWeek values.
 * 
 */
public final class DayOfTheWeekCollection extends ComplexProperty implements
		Iterable<DayOfTheWeek> {

	/** The items. */
	private List<DayOfTheWeek> items = new ArrayList<DayOfTheWeek>();

	/**
	 * Initializes a new instance of the class.
	 */
	protected DayOfTheWeekCollection() {
	}

	/**
	 * Convert to string.
	 * 
	 * @param separator
	 *            the separator
	 * @return String representation of collection.
	 */
	private String toString(String separator) {
		if (this.getCount() == 0) {
			return "";
		} else {
			// String[] daysOfTheWeekArray = new String[this.getCount()];
			StringBuffer daysOfTheWeekstr = new StringBuffer();

			for (int i = 0; i < this.getCount(); i++) {
				// daysOfTheWeekArray[i] = items.get(i).toString();
				if (daysOfTheWeekstr.length() == 0) {
					daysOfTheWeekstr.append(items.get(i).toString());
				} else {
					daysOfTheWeekstr.append(separator);
					daysOfTheWeekstr.append(items.get(i).toString());
				}
			}

			return daysOfTheWeekstr.toString();
		}
	}

	/**
	 * Loads from XML.
	 * 
	 * @param reader
	 *            The reader.
	 * @param xmlElementName
	 *            Name of the XML element.
	 * @throws Exception
	 *             the exception
	 */
	protected void loadFromXml(EwsServiceXmlReader reader, 
			String xmlElementName)
			throws Exception {
		reader.ensureCurrentNodeIsStartElement(XmlNamespace.Types,
				xmlElementName);
		EwsUtilities.parseEnumValueList(DayOfTheWeek.class, this.items, reader
				.readElementValue(), ' ');
	}

	/**
	 * * Gets the request version.
	 * 
	 * @param writer
	 *            the writer
	 * @param xmlElementName
	 *            the xml element name
	 * @throws XMLStreamException
	 *             the xML stream exception
	 * @throws ServiceXmlSerializationException
	 *             the service xml serialization exception
	 */
	@Override
	protected void writeToXml(EwsServiceXmlWriter writer, String xmlElementName)
			throws XMLStreamException, ServiceXmlSerializationException {
		String daysOfWeekAsString = this.toString(" ");

		if (!(daysOfWeekAsString == null || daysOfWeekAsString.isEmpty())) {
			writer.writeElementValue(XmlNamespace.Types,
					XmlElementNames.DaysOfWeek, daysOfWeekAsString);
		}
	}

	/***
	 * Builds string representation of the collection.
	 * 
	 * @return A comma-delimited string representing the collection.
	 */
	@Override
	public String toString() {
		return this.toString(",");
	}

	/**
	 * Adds a day to the collection if it is not already present.
	 * 
	 * @param dayOfTheWeek
	 *            The day to add.
	 */
	public void add(DayOfTheWeek dayOfTheWeek) {
		if (!this.items.contains(dayOfTheWeek)) {
			this.items.add(dayOfTheWeek);
			this.changed();
		}
	}

	/**
	 * Adds multiple days to the collection if they are not already present.
	 * 
	 * @param daysOfTheWeek
	 *            The days to add.
	 */
	public void addRange(Iterator<DayOfTheWeek> daysOfTheWeek) {
		while (daysOfTheWeek.hasNext()) {
			this.add(daysOfTheWeek.next());
		}
	}

	/**
	 * Clears the collection.
	 */
	public void clear() {
		if (this.getCount() > 0) {
			this.items.clear();
			this.changed();
		}
	}

	/**
	 * Remove a specific day from the collection.
	 * 
	 * @param dayOfTheWeek
	 *            the day of the week
	 * @return True if the day was removed from the collection, false otherwise.
	 */
	public boolean remove(DayOfTheWeek dayOfTheWeek) {
		boolean result = this.items.remove(dayOfTheWeek);

		if (result) {
			this.changed();
		}
		return result;
	}

	/**
	 * Removes the day at a specific index.
	 * 
	 * @param index
	 *            the index
	 * @throws ArgumentOutOfRangeException
	 *             the argument out of range exception
	 */
	public void removeAt(int index) throws ArgumentOutOfRangeException {
		if (index < 0 || index >= this.getCount()) {
			throw new ArgumentOutOfRangeException("index",
					Strings.IndexIsOutOfRange);
		}

		this.items.remove(index);
		this.changed();
	}

	/**
	 * Gets the DayOfTheWeek at a specific index in the collection.
	 * 
	 * @param index
	 *            the index
	 * @return DayOfTheWeek at index
	 */
	public DayOfTheWeek getWeekCollectionAtIndex(int index) {
		return this.items.get(index);
	}

	/**
	 * Gets the number of days in the collection.
	 * 
	 * @return the count
	 */
	public int getCount() {
		return this.items.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<DayOfTheWeek> iterator() {
		return this.items.iterator();
	}

}
