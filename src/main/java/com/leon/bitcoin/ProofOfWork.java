package com.leon.bitcoin;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.time.Instant;

public class ProofOfWork
{
    public ProofOfWork() {}

    public static void main()
    {
        double difficulty;
        String result = "";
        ProofOfWork pow = new ProofOfWork();

        for(int difficulty_bits = 0;  difficulty_bits < 22; ++difficulty_bits)
        {
            difficulty = Math.pow(2, difficulty_bits);

            System.out.println("\n\nDifficulty: " + difficulty + " and difficulty bits: " +  difficulty_bits);
            System.out.println("Start searching...");

            Instant start = Instant.now();

            result = pow.prove("test block with transactions" + result, difficulty_bits);

            Instant finish = Instant.now();

            long timeElapsed = Duration.between(start, finish).toMillis();

            System.out.println("Searching ended after " + timeElapsed + " ms");
        }
    }


    public String prove(String header, int difficulty_bits)
    {
        double target = Math.pow(2, 256 - difficulty_bits);
        double max_nonce = Math.pow(2,22);
        String hashResultInHex = "";
        System.out.println("Difficulty bit is " + difficulty_bits + " and target is " + target);

        for(int nonce = 0; nonce < max_nonce - 1 ; nonce++)
        {
            byte[] hashResult = sha256(header + nonce);
            hashResultInHex = bytesToHex(hashResult);
            BigInteger bigIntegerHashResult = new BigInteger(hashResultInHex, 16);
            BigInteger bigIntegerTarget = BigDecimal.valueOf(target).toBigInteger();

            if(bigIntegerHashResult.compareTo(bigIntegerTarget) == -1)
            {
                System.out.println("SHA256 of "  + header + " is 0x" + hashResultInHex);
                System.out.println("BigInteger of hash result: "  + bigIntegerHashResult.toString());
                System.out.println("BigInteger of target: "  + bigIntegerTarget.toString());
                System.out.println("SUCCESS! Hash result is less than target when nonce = " + nonce);
                return hashResultInHex;
            }
        }
        System.out.println("FAILURE! Hash result is greater than or equal to the target");
        return hashResultInHex;
    }

    private static String bytesToHex(byte[] hash)
    {
        StringBuilder hexString = new StringBuilder(2 * hash.length);

        for (int i = 0; i < hash.length; i++)
        {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    private static byte[] sha256(String stringToHash)
    {
        byte[] hashedResult = null;
        try
        {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            hashedResult = digest.digest(stringToHash.getBytes(StandardCharsets.UTF_8));
        }
        catch(NoSuchAlgorithmException nsae)
        {
            System.out.println("Exception thrown:" + nsae.getMessage());
        }
        return hashedResult;
    }

}
