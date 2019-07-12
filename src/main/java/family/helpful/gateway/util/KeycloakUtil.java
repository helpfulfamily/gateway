package family.helpful.gateway.util;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;

public class KeycloakUtil {
 public static  String getUserName(KeycloakAuthenticationToken kat){
      // In order to get user via Keycloak
      KeycloakPrincipal keycloakPrincipal= (KeycloakPrincipal) kat.getPrincipal();
      AccessToken token= keycloakPrincipal.getKeycloakSecurityContext().getToken();
      String senderUsername= token.getPreferredUsername();
      return senderUsername;
  }
}
