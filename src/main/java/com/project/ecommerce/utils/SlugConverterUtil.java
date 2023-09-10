package com.project.ecommerce.utils;

public class SlugConverterUtil {

    public static String convertToSlug(String text) {
        if (text == null) {
            return "";
        }

        String slug = text.trim().toLowerCase().replaceAll("\\s+", "-");

        slug = slug.replaceAll("[^a-z0-9\\-]", "");

        slug = slug.replaceAll("-+", "-");

        return slug;
    }

}
