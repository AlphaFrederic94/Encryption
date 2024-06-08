 # This project contains an implementation of the RSA ans AES encrpytion algorithm in Java
 
- # Key Features For rsa
- Key Generation: Generates a 2048-bit RSA key pair (public and private keys) upon object creation.
- Encryption: Uses the RSA public key to encrypt data.
  - Initializes an RSA Cipher in encryption mode with the public key.
  - Converts the input data into bytes using UTF-8 encoding.
  - Encrypts the byte data using the Cipher's doFinal method.
  - Encodes the encrypted bytes to a Base64 string for easy transmission and storage.
- Decryption: Uses the RSA private key to decrypt data.
    - Initializes an RSA Cipher in decryption mode with the private key.
    - Decodes the Base64 string to get the encrypted byte data.
    - Decrypts the byte data using the Cipher's doFinal method.
    - Converts the decrypted bytes back to a String using UTF-8 encoding.
 
  And same goes for the AES. I didn't want to complicate this but I later intend to add new features to this.
  I might make it as complex as possible and try add a unique feature.Already have the idea it won
