import java.net.Authenticator;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.xml.bind.JAXBElement;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Holder;

import org.apache.cxf.configuration.jsse.TLSClientParameters;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.microsoft.schemas.exchange.services._2006.messages.ArrayOfResponseMessagesType;
import com.microsoft.schemas.exchange.services._2006.messages.ExchangeService;
import com.microsoft.schemas.exchange.services._2006.messages.ExchangeServicePortType;
import com.microsoft.schemas.exchange.services._2006.messages.FindItemResponseMessageType;
import com.microsoft.schemas.exchange.services._2006.messages.FindItemResponseType;
import com.microsoft.schemas.exchange.services._2006.messages.FindItemType;
import com.microsoft.schemas.exchange.services._2006.messages.GetFolderResponseType;
import com.microsoft.schemas.exchange.services._2006.messages.GetFolderType;
import com.microsoft.schemas.exchange.services._2006.messages.ResponseMessageType;
import com.microsoft.schemas.exchange.services._2006.types.BaseFolderIdType;
import com.microsoft.schemas.exchange.services._2006.types.ConnectingSIDType;
import com.microsoft.schemas.exchange.services._2006.types.DefaultShapeNamesType;
import com.microsoft.schemas.exchange.services._2006.types.DistinguishedFolderIdNameType;
import com.microsoft.schemas.exchange.services._2006.types.DistinguishedFolderIdType;
import com.microsoft.schemas.exchange.services._2006.types.ExchangeImpersonationType;
import com.microsoft.schemas.exchange.services._2006.types.FolderResponseShapeType;
import com.microsoft.schemas.exchange.services._2006.types.ItemQueryTraversalType;
import com.microsoft.schemas.exchange.services._2006.types.ItemResponseShapeType;
import com.microsoft.schemas.exchange.services._2006.types.ItemType;
import com.microsoft.schemas.exchange.services._2006.types.MessageType;
import com.microsoft.schemas.exchange.services._2006.types.NonEmptyArrayOfBaseFolderIdsType;
import com.microsoft.schemas.exchange.services._2006.types.SerializedSecurityContextType;

/**
 * This is just a proof of concept to show how to use Exchange Web Services
 * (EWS) to list total and unread inbox items.
 * 
 * @author nestor
 * 
 */
public class InboxSummaryTest {

	private static final Logger log = LoggerFactory
			.getLogger(InboxSummaryTest.class);

	@Test
    public void testInbox() throws MalformedURLException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
        
    	   	
        //This works with the default URL included in the wsdl file from which the Stubs were generated
        //ExchangeService service = new ExchangeService(); 
        
        
        Authenticator.setDefault(new Authenticator() {
        	 @Override
        	 protected PasswordAuthentication getPasswordAuthentication() {
        	   return new PasswordAuthentication(
        	     "test",
        	     "test".toCharArray());
        	 }
        });
        
        
        
        
        //This should be used to make the code environment independent
        EwsSettings ewsSettings = EwsSettings.getInstance();
        URL wsdlURL = this.getClass().getClassLoader().getResource(ewsSettings.getWsdlPath());
        //URL wsdlURL = new URL(ewsSettings.getWsdlPath());
        //ExchangeService service = new ExchangeService(wsdlURL, new QName("http://schemas.microsoft.com/exchange/services/2006/messages", ewsSettings.getServiceStubName()));
        ExchangeService service = new ExchangeService();
        
        ExchangeServicePortType port = service.getExchangeServicePort();
        BindingProvider provider = (BindingProvider)port;
        // You can set the address per request here
        provider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
             "http://localhost:8080/ews/exchange.asmx");
        
        Client client = ClientProxy.getClient(port);
        HTTPConduit http = (HTTPConduit) client.getConduit();

        HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy();
        httpClientPolicy.setConnectionTimeout(36000);
        httpClientPolicy.setAllowChunking(false);
        httpClientPolicy.setReceiveTimeout(32000);
        http.setClient(httpClientPolicy);

     // Create a trust manager that does not validate certificate chains
    	TrustManager[] trustAllCerts = new TrustManager[] { 
    	    new X509TrustManager() {     
    	        public java.security.cert.X509Certificate[] getAcceptedIssuers() { 
    	            return null;
    	        } 
    	        public void checkClientTrusted( 
    	            java.security.cert.X509Certificate[] certs, String authType) {
    	            } 
    	        public void checkServerTrusted( 
    	            java.security.cert.X509Certificate[] certs, String authType) {
    	        }
    	    } 
    	}; 

    	// Install the all-trusting trust manager
    	SSLContext sc = SSLContext.getInstance("SSL"); 
    	try {
    	    sc.init(null, trustAllCerts, new java.security.SecureRandom()); 
    	    HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
    	} catch (GeneralSecurityException e) {
    	}
    	
        // Use the created socket for cxf 
        TLSClientParameters tlsParam = new TLSClientParameters();
        tlsParam.setSSLSocketFactory(sc.getSocketFactory());
        http.setTlsClientParameters(tlsParam);
        
        GetFolderType request = new GetFolderType();
        
        FolderResponseShapeType folderShape = new FolderResponseShapeType();
        folderShape.setBaseShape(DefaultShapeNamesType.ALL_PROPERTIES);

        request.setFolderShape(folderShape);
        
        DistinguishedFolderIdType inbox = new DistinguishedFolderIdType();
        inbox.setId(DistinguishedFolderIdNameType.INBOX);
        NonEmptyArrayOfBaseFolderIdsType folderIds = new NonEmptyArrayOfBaseFolderIdsType();
        List<BaseFolderIdType> ids = folderIds.getFolderIdOrDistinguishedFolderId();
        ids.add(inbox);
        
        request.setFolderIds(folderIds);
        
        //We do not use the below and that is why we need to overload the corresponding methods
        ExchangeImpersonationType impersonation = new ExchangeImpersonationType();
        ConnectingSIDType connSID = new ConnectingSIDType();
        connSID.setPrincipalName("test");
        
        impersonation.setConnectingSID(connSID);
        SerializedSecurityContextType s2SAuth = new SerializedSecurityContextType();
        s2SAuth.setUserSid("test");
        
        
        Holder<GetFolderResponseType> folderResult = new Holder<GetFolderResponseType>(); //Renamed from original getFolderResult to respect Java conventions

        
        //request.setItemIds(value)
        
        /*port.getFolder(request, null, null, null, ewsSettings.getRequestServerVersion(), folderResult, ewsSettings.getServerVersionInfoHolder());
        //port.getFolder(request, mailboxCulture, ewsSettings.getRequestServerVersion(), folderResult, ewsSettings.getServerVersionInfoHolder());
        
        GetFolderResponseType folderResponseType = folderResult.value;
        ArrayOfResponseMessagesType arrayOfResponseMessagesType = folderResponseType.getResponseMessages();
        List<JAXBElement<? extends ResponseMessageType>> responseMessageTypeList = arrayOfResponseMessagesType.getCreateItemResponseMessageOrDeleteItemResponseMessageOrGetItemResponseMessage();
        for (JAXBElement<? extends ResponseMessageType> jaxBElement : responseMessageTypeList) {
            FolderInfoResponseMessageType response = (FolderInfoResponseMessageType) jaxBElement.getValue();
            ArrayOfFoldersType arrayOfFolderTypes = response.getFolders();
            //List<BaseFolderType> folderTypes = 
            List<BaseFolderType> folderTypes = arrayOfFolderTypes.getFolderOrCalendarFolderOrContactsFolder();
            for( BaseFolderType baseFolderType : folderTypes ) {
                FolderType folderType = (FolderType) baseFolderType;
                String displayName = folderType.getDisplayName();
                int totalCount = folderType.getTotalCount();
                int unread = folderType.getUnreadCount();
                log.debug(displayName + ":");
                log.debug("  Unread: " + unread ); 
                log.debug("  Total: " + totalCount ); 
            }
            //log.debug(folderResult.value.getResponseMessages());
            
        }*/

        FindItemType reqFindItem = new FindItemType();
        reqFindItem.setTraversal(ItemQueryTraversalType.SHALLOW);
        
        ItemResponseShapeType itemShape = new ItemResponseShapeType();
        itemShape.setBaseShape(DefaultShapeNamesType.ALL_PROPERTIES);
        reqFindItem.setItemShape(itemShape);
        
        reqFindItem.setParentFolderIds(folderIds);
        
        Holder<FindItemResponseType> itemResult = new Holder<FindItemResponseType>();
        port.findItem(reqFindItem, null, null, null, ewsSettings.getRequestServerVersion(), itemResult, ewsSettings.getServerVersionInfoHolder());
        
        
        FindItemResponseType resp = itemResult.value;
        ArrayOfResponseMessagesType respMess = resp.getResponseMessages();
        List<JAXBElement<? extends ResponseMessageType>> messages = respMess.getCreateItemResponseMessageOrDeleteItemResponseMessageOrGetItemResponseMessage();
        for (JAXBElement<? extends ResponseMessageType> jaxbElement : messages) {
        	FindItemResponseMessageType itemMessage = (FindItemResponseMessageType)jaxbElement.getValue();
        	for(ItemType item : itemMessage.getRootFolder().getItems().getItemOrMessageOrCalendarItem()) {
        		MessageType message = (MessageType)item;
        		System.out.println(message.getSubject());
        	}
		}
        
        //port.getFolder(request, impersonation, s2SAuth, mailboxCulture, requestVersion, getFolderResult, serverVersion);
        //port.sendItem(request, impersonation, s2SAuth, mailboxCulture, requestVersion, sendItemResult, serverVersion)
    }
}