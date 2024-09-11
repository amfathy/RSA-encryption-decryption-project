# RSA Encryption and Decryption

This is a Java implementation of RSA encryption and decryption, a widely used public-key cryptosystem. The program encrypts and decrypts a message using two prime numbers `p` and `q`, and it supports both standard RSA decryption and decryption using the Chinese Remainder Theorem (CRT).

## Features
- Generates public and private keys from two user-provided prime numbers.
- Encrypts and decrypts a message using RSA.
- Performs decryption using the Chinese Remainder Theorem (CRT) for efficiency.
- Validates that the input numbers `p` and `q` are prime.
- Provides detailed console outputs for each step of the encryption and decryption process.

## How It Works

1. **Prime Number Input**: The user provides two prime numbers `p` and `q`. The program calculates the RSA modulus `n = p * q` and Euler's totient function `z = (p-1) * (q-1)`.
2. **Key Generation**: The program generates a public key `e` such that `gcd(e, z) = 1`, and the private key `d` is calculated as the modular inverse of `e` modulo `z`.
3. **Encryption**: The input message is converted to its ASCII values. Each character is encrypted using the public key (`e`) with the formula `c = m^e % n`, where `m` is the ASCII value of the character.
4. **Decryption**: The ciphertext is decrypted using the private key (`d`) with the formula `m = c^d % n`, where `c` is the encrypted character.
5. **Decryption with CRT**: The program also implements decryption using the Chinese Remainder Theorem for faster decryption when working with large prime numbers.

## Code Explanation

### `main` Method
- Prompts the user to input two prime numbers `p` and `q` and a message to encrypt.
- Validates if the provided numbers are prime.
- Calls the `encryptionANDdecryption` method to perform RSA encryption and decryption.

### `encryptionANDdecryption` Method
- Performs RSA key generation, encryption, and decryption.
- Converts the input message to its ASCII values, encrypts each character, and then decrypts them back to their original form.
- Prints the encrypted and decrypted messages, as well as the results of decryption using CRT.

### Key Utility Methods
- `getE(z)`: Finds a valid public key `e` such that `gcd(e, z) = 1`.
- `gcd(e, z)`: Computes the greatest common divisor (GCD) using the Euclidean algorithm.
- `invPow(a, b)`: Finds the modular inverse of `a` mod `b`.
- `squareANDmultiply(e, m, n)`: Performs fast exponentiation using the Square-and-Multiply algorithm.
- `crt(p, q, y, d)`: Decrypts a message using the Chinese Remainder Theorem (CRT).
- `mPower(x, e, m)`: Computes `x^e % m` efficiently for prime testing.

## Getting Started

### Prerequisites
- Java Development Kit (JDK) 8 or higher.
- A Java IDE or a command-line interface to compile and run the program.

### Running the Program

1. Clone the repository:
   ```bash
   git clone https://github.com/amfathy/rsa-encryption.git
   cd rsa-encryption
