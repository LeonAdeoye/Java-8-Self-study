package com.leon.chronicleMap;

import net.openhft.chronicle.core.values.LongValue;
import net.openhft.chronicle.map.ChronicleMap;
import net.openhft.chronicle.values.Values;

public class ChronicleMapper
{
    public static void main(String... args)
    {
        ChronicleMap<LongValue, CharSequence> inMemoryCountryMap = ChronicleMap
                .of(LongValue.class, CharSequence.class)
                .name("country-map")
                .entries(50)
                .averageValue("America")
                .create();

        LongValue qatarKey = Values.newHeapInstance(LongValue.class);
        qatarKey.setValue(1);
        inMemoryCountryMap.put(qatarKey, "Qatar");

        CharSequence country = inMemoryCountryMap.get(qatarKey);
        System.out.println(country.toString());

        inMemoryCountryMap.close();
    }
}
