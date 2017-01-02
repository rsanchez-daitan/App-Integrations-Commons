/**
 * Copyright 2016-2017 Symphony Integrations - Symphony LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.symphonyoss.integration.model.yaml;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Base data model for the YAML config file, used to read provisioning info for Integration
 * Bridge and other info as well, related to the services that Integration Bridge interacts with (as
 * Symphony Agent, POD API and Key Manager).
 * Created by rsanchez on 10/11/16.
 */
@Configuration
@ConfigurationProperties
public class IntegrationProperties {

  private ConnectionInfo pod;

  private ConnectionInfo agent;

  private ConnectionInfo podSessionManager;

  private ConnectionInfo keyManager;

  private CloudLogging cloudLogging;

  private Map<String, Application> applications = new HashMap<>();

  private IntegrationBridge integrationBridge;

  private Certificate signingCert;

  public ConnectionInfo getPod() {
    return pod;
  }

  public void setPod(ConnectionInfo pod) {
    this.pod = pod;
  }

  public ConnectionInfo getAgent() {
    return agent;
  }

  public void setAgent(ConnectionInfo agent) {
    this.agent = agent;
  }

  public ConnectionInfo getKeyManager() {
    return keyManager;
  }

  public void setKeyManager(ConnectionInfo keyManager) {
    this.keyManager = keyManager;
  }

  public ConnectionInfo getPodSessionManager() {
    return podSessionManager;
  }

  public void setPodSessionManager(ConnectionInfo podSessionManager) {
    this.podSessionManager = podSessionManager;
  }

  public CloudLogging getCloudLogging() {
    return cloudLogging;
  }

  public void setCloudLogging(CloudLogging cloudLogging) {
    this.cloudLogging = cloudLogging;
  }

  public Map<String, Application> getApplications() {
    return applications;
  }

  public void setApplications(
      Map<String, Application> applications) {
    this.applications = applications;
  }

  public Application getApplication(String component) {
    for (Application application : applications.values()) {
      if (component.equals(application.getComponent())) {
        return application;
      }
    }

    return null;
  }

  /**
   * Get application identifier based on application component name
   * @param component Application component name
   * @return Application identifier
   */
  public String getApplicationId(String component) {
    for (Map.Entry<String, Application> entry : applications.entrySet()) {
      if (component.equals(entry.getValue().getComponent())) {
        return entry.getKey();
      }
    }

    return component;
  }

  public IntegrationBridge getIntegrationBridge() {
    return integrationBridge;
  }

  public void setIntegrationBridge(
      IntegrationBridge integrationBridge) {
    this.integrationBridge = integrationBridge;
  }

  public Certificate getSigningCert() {
    return signingCert;
  }

  public void setSigningCert(Certificate signingCert) {
    this.signingCert = signingCert;
  }

  /**
   * Get the global whitelist based on YAML file settings.
   * @return Global origin whitelist
   */
  public Set<String> getGlobalWhiteList() {
    if (integrationBridge == null) {
      return Collections.emptySet();
    }

    return integrationBridge.getWhiteList();
  }

  public String getSymphonyUrl() {
    return String.format("https://%s:%s", pod.getHost(), pod.getPort());
  }

  public String getPodUrl() {
    return String.format("https://%s:%s/pod", pod.getHost(), pod.getPort());
  }

  public String getLoginUrl() {
    return String.format("https://%s:%s/login", pod.getHost(), pod.getPort());
  }

  public String getSessionManagerAuthUrl() {
    return String.format("https://%s:%s/sessionauth", podSessionManager.getHost(),
        podSessionManager.getPort());
  }

  public String getKeyManagerUrl() {
    return String.format("https://%s:%s/relay", keyManager.getHost(), keyManager.getPort());
  }

  public String getKeyManagerAuthUrl() {
    return String.format("https://%s:%s/keyauth", keyManager.getHost(), keyManager.getAuthPort());
  }

  public String getAgentUrl() {
    return String.format("https://%s:%s/agent", agent.getHost(), agent.getPort());
  }

  @Override
  public String toString() {
    return "IntegrationProperties{" +
        "pod=" + pod +
        ", agent=" + agent +
        ", sessionManager=" + podSessionManager +
        ", keyManager=" + keyManager +
        '}';
  }
}