package com.hwd.springframework.beans.factory;

import com.hwd.springframework.beans.factory.PropertyValue;

import java.util.ArrayList;
import java.util.List;

public class PropertyValues {
    List<PropertyValue> propertyValueList = new ArrayList<>(10);

    public void addPropertyValue(PropertyValue propertyValue) {
        this.propertyValueList.add(propertyValue);
    }

    public List<PropertyValue> getPropertyValueList() {
        return propertyValueList;
    }

    public PropertyValue getPropertyValue(String propertyName) {
        for (PropertyValue propertyValue : propertyValueList) {
            if(propertyValue.getName().equals(propertyName)) {
                return propertyValue;
            }
        }
        return null;
    }
}
