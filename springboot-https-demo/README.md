## Bash 

### PKCS12: Public Key Cryptographic Standards
- `keytool -genkeypair -alias europa -keyalg RSA -keysize 2048 -storetype PKCS12 -keystore europa.p12 -validity 3650`

## OR

### JKSL: Java Keystore
- `keytool -genkeypair -alias europa -keyalg RSA -keysize 2048 -keystore europa.jks -validity 3650`
**Note: SAN, Subject Alternative is needed, so do the following instead**
  - `Certificate for <localhost> doesn't match common name of the certificate subject:` Exception occurs 
  - `keytool -genkeypair -keyalg RSA -keysize 2048 -alias europa -dname "CN=europa,OU=Hakan,O=Hakan,C=NL" -ext "SAN:c=DNS:localhost,IP:127.0.0.1" -validity 3650 -keystore europa.jks -storepass password -keypass password`

```bash
 [422] → keytool -genkeypair -alias europa -keyalg RSA -keysize 2048 -keystore europa.jks -validity 3650
Enter keystore password:
Re-enter new password:
What is your first and last name?
  [Unknown]:  XXXXXXXXXX
What is the name of your organizational unit?
  [Unknown]:  Europa
What is the name of your organization?
  [Unknown]:  Europa
What is the name of your City or Locality?
  [Unknown]:  XXXXXXX
What is the name of your State or Province?
  [Unknown]:  XX
What is the two-letter country code for this unit?
  [Unknown]:  XX
Is CN=XXXXXXXXXX, OU=Europa, O=Europa, L=XXXXXXX, ST=XX, C=XX correct?
  [no]:  yes

Enter key password for <europa>
	(RETURN if same as keystore password):
Re-enter new password:

Warning:
The JKS keystore uses a proprietary format. It is recommended to migrate to PKCS12 which is an industry standard format using "keytool -importkeystore -srckeystore europa.jks -destkeystore europa.jks -deststoretype pkcs12".  
```

- It is recommended to use the PKCS12 format which is an industry standard format. So in case we already have a JKS keystore, we can convert it to PKCS12 format using the following command:
    `keytool -importkeystore -srckeystore europa.jks -destkeystore europa.p12 -deststoretype pkcs12`
  

## Testing

1. Fails for plain curl
```bash
 [428] → curl  https://localhost:8080/random/int
curl: (60) SSL certificate problem: self signed certificate
More details here: https://curl.haxx.se/docs/sslcerts.html

curl failed to verify the legitimacy of the server and therefore could not
establish a secure connection to it. To learn more about this situation and
how to fix it, please visit the web page mentioned above.
```
2. Use -k option

```bash
 [429] → curl -k https://localhost:8080/random/int
{"randomNumber":81}
```

## Browser
- Going to `https://localhost:8080/random/int`, will say not safe, Click Advanced
- On MacOS, if Chrome doesn't give a proceed button, click anywhere on the page and type `thisisunsafe`

## References:
- https://www.baeldung.com/spring-boot-https-self-signed-certificate
- https://mkyong.com/spring-boot/spring-boot-ssl-https-examples/
- https://www.sslshopper.com/article-how-to-create-a-self-signed-certificate-using-java-keytool.html

- https://stackoverflow.com/questions/652916/converting-a-java-keystore-into-pem-format