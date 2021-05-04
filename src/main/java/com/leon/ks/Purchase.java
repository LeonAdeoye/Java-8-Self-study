package com.leon.ks;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Purchase
{
    private  String customerID;
    private double amountSpend;
    private List<String> itemsPurchased;
    private LocalDateTime transactionTime;
    private String creditCardNumber;
    private String postCode;

    public String getCustomerID()
    {
        return customerID;
    }

    public void setCustomerID(String customerID)
    {
        this.customerID = customerID;
    }

    public double getAmountSpend()
    {
        return amountSpend;
    }

    public void setAmountSpend(double amountSpend)
    {
        this.amountSpend = amountSpend;
    }

    public List<String> getItemsPurchased()
    {
        return itemsPurchased;
    }

    public void setItemsPurchased(List<String> itemsPurchased)
    {
        this.itemsPurchased = itemsPurchased;
    }

    public LocalDateTime getTransactionTime()
    {
        return transactionTime;
    }

    public void setTransactionTime(LocalDateTime transactionTime)
    {
        this.transactionTime = transactionTime;
    }

    public String getCreditCardNumber()
    {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber)
    {
        this.creditCardNumber = creditCardNumber;
    }

    public String getPostCode()
    {
        return postCode;
    }

    public void setPostCode(String postCode)
    {
        this.postCode = postCode;
    }

    @Override
    public boolean equals(Object o)
    {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Purchase purchase = (Purchase) o;
        return Double.compare(purchase.getAmountSpend(), getAmountSpend()) == 0 &&
                Objects.equals(getCustomerID(), purchase.getCustomerID()) &&
                Objects.equals(getItemsPurchased(), purchase.getItemsPurchased()) &&
                Objects.equals(getTransactionTime(), purchase.getTransactionTime()) &&
                Objects.equals(getPostCode(), purchase.getPostCode()) &&
                Objects.equals(getCreditCardNumber(), purchase.getCreditCardNumber());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getCustomerID(), getAmountSpend(), getItemsPurchased(), getTransactionTime(), getCreditCardNumber());
    }

    @Override
    public String toString()
    {
        return "Purchase{" +
                "customerID='" + customerID + '\'' +
                ", amountSpend=" + amountSpend +
                ", postCode=" + postCode +
                ", itemsPurchased=" + itemsPurchased +
                ", transactionTime=" + transactionTime +
                ", creditCardNumber='" + creditCardNumber + '\'' +
                '}';
    }
}
