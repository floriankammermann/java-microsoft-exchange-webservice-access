package com.microsoft.ews;

import java.net.URI;

import microsoft.exchange.webservices.data.BasePropertySet;
import microsoft.exchange.webservices.data.EmailMessage;
import microsoft.exchange.webservices.data.ExchangeCredentials;
import microsoft.exchange.webservices.data.ExchangeService;
import microsoft.exchange.webservices.data.ExchangeVersion;
import microsoft.exchange.webservices.data.FindItemsResults;
import microsoft.exchange.webservices.data.Item;
import microsoft.exchange.webservices.data.ItemSchema;
import microsoft.exchange.webservices.data.ItemView;
import microsoft.exchange.webservices.data.LogicalOperator;
import microsoft.exchange.webservices.data.MessageBody;
import microsoft.exchange.webservices.data.PropertySet;
import microsoft.exchange.webservices.data.SearchFilter;
import microsoft.exchange.webservices.data.SortDirection;
import microsoft.exchange.webservices.data.WebCredentials;
import microsoft.exchange.webservices.data.WellKnownFolderName;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServiceExchangeTest {

	private static Logger log = LoggerFactory.getLogger(ServiceExchangeTest.class);

	@Test
	public void testGetMail() throws Exception {

		ExchangeService service = new ExchangeService(ExchangeVersion.Exchange2007_SP1);

		// Setting credentials is unnecessary when you connect from a computer
		// that is logged on to the domain.

		ExchangeCredentials credentials = new WebCredentials("test", "test");
		service.setCredentials(credentials);
		service.setUrl(new URI("http://localhost:8080/EWS/Exchange.asmx"));
		
		// set a proxy
		// service.setWebProxy(new WebProxy("proxy.evd.admin.ch", 8080));

		service.setTraceEnabled(true);

		pageThroughEntireInbox(service);

	}

	public void sendMail(ExchangeService service) throws Exception {
		EmailMessage msg = new EmailMessage(service);
		msg.setSubject("From EWS");
		msg.setBody(MessageBody.getMessageBodyFromText("Using the Microsoft EWS Managed API"));
		msg.getToRecipients().add("test.test@test.com");
		msg.send();
	}

	public void pageThroughEntireInbox(ExchangeService service) throws Exception {
		ItemView view = new ItemView(50);
		FindItemsResults<Item> findResults;

		do {
			findResults = service.findItems(WellKnownFolderName.Inbox, view);

			for (Item item : findResults.getItems()) {
				log.debug(item.getSubject());
			}

			view.setOffset(view.getOffset() + 50);
		} while (findResults.isMoreAvailable());
	}

	public void findItems(ExchangeService service) throws Exception {
		ItemView view = new ItemView(10);
		view.getOrderBy().add(ItemSchema.DateTimeReceived, SortDirection.Ascending);
		view.setPropertySet(new PropertySet(BasePropertySet.IdOnly, ItemSchema.Subject, ItemSchema.DateTimeReceived));

		FindItemsResults<Item> findResults = service.findItems(WellKnownFolderName.Inbox, new SearchFilter.SearchFilterCollection(LogicalOperator.Or, new SearchFilter.ContainsSubstring(
				ItemSchema.Subject, "EWS"), new SearchFilter.ContainsSubstring(ItemSchema.Subject, "API")), view);

		System.out.println("Total number of items found: " + findResults.getTotalCount());

		for (Item item : findResults) {
			log.debug(item.getSubject());
			log.debug(item.getBody().toString());
		}
	}

}
