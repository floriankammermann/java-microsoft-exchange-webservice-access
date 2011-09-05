package com.microsoft;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.xml.ws.Holder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.microsoft.schemas.exchange.services._2006.types.ExchangeVersionType;
import com.microsoft.schemas.exchange.services._2006.types.RequestServerVersion;
import com.microsoft.schemas.exchange.services._2006.types.ServerVersionInfo;

/**
 * Singleton holding settings. Should in reality pull the values from the environment
 * @author nestor
 *
 */
public class EwsSettings {
    private static final Logger log = LoggerFactory.getLogger(EwsSettings.class);
    
    private String propertiesPath = "src/test/resources/ews.properties";
    private String wsdlPath;
    private String serviceStubName;
    private String userName;
    private String password;
    private RequestServerVersion requestServerVersion;
    private Holder<ServerVersionInfo> serverVersionInfoHolder;

    private EwsSettings() {
        
        try {
            FileInputStream propFile = new FileInputStream(propertiesPath);
            Properties p = new Properties(System.getProperties());
            p.load(propFile);
            wsdlPath = p.getProperty("ews.wsdlPath");
            serviceStubName = p.getProperty("ews.serviceStubName");
            userName = p.getProperty("ews.userName");
            password = p.getProperty("ews.password");

            requestServerVersion = new RequestServerVersion();
            requestServerVersion.setVersion(ExchangeVersionType.EXCHANGE_2007_SP_1);
            
            ServerVersionInfo serverVersionInfo = new ServerVersionInfo();
            serverVersionInfoHolder = new Holder<ServerVersionInfo>(serverVersionInfo);
        } catch (IOException e) {
            log.error("Failed to load ews properties", e);
        }
    }
    
    private static class EwsSettingsHolder {
        public static final EwsSettings INSTANCE = new EwsSettings();
    }
    
    public static EwsSettings getInstance() {
        return EwsSettingsHolder.INSTANCE;
    }

    public String getWsdlPath() {
        return wsdlPath;
    }
    
    public String getServiceStubName() {
        return serviceStubName;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
    
    public RequestServerVersion getRequestServerVersion() {
        return requestServerVersion;
    }
    
    public Holder<ServerVersionInfo> getServerVersionInfoHolder() {
        return serverVersionInfoHolder;
    }
    
}