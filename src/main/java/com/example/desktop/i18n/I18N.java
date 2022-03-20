/*
 * Copyright (C) 2022 Parisi Alessandro
 * This file is part of MaterialFX (https://github.com/palexdev/MaterialFX).
 *
 * MaterialFX is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MaterialFX is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with MaterialFX.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.example.desktop.i18n;

import io.github.palexdev.materialfx.i18n.Language;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;


public class I18N {

    private static final ObjectProperty<Locale> locale = new SimpleObjectProperty<>();

    static {
        setLanguage(Language.defaultLanguage());
        locale.addListener(invalidated -> Locale.setDefault(getLocale()));
    }

    public static String get(String key, Object... args) {
        ResourceBundle bundle = getBundle(getLocale());
        return MessageFormat.format(bundle.getString(key), args);
    }


    public static String get(Language language, String key, Object... args) {
        ResourceBundle bundle = getBundle(language.getLocale());
        return MessageFormat.format(bundle.getString(key), args);
    }


    public static String getOrDefault(String key, Object... args) {
        ResourceBundle bundle = getBundle(getLocale());
        try {
            String s = bundle.getString(key);
            return MessageFormat.format(s, args);
        } catch (Exception ex) {
            return get(Language.defaultLanguage(), key, args);
        }
    }


    public static String getOrDefault(Language language, String key, Object... args) {
        ResourceBundle bundle = getBundle(language.getLocale());
        try {
            String s = bundle.getString(key);
            return MessageFormat.format(s, args);
        } catch (Exception ex) {
            return get(Language.defaultLanguage(), key, args);
        }
    }


    public static String getOrDefault(String key, String def, Object... args) {
        ResourceBundle bundle = getBundle(getLocale());
        try {
            String s = bundle.getString(key);
            return MessageFormat.format(s, args);
        } catch (Exception ex) {
            return def;
        }
    }

    public static String getOrDefault(Language language, String key, String def, Object... args) {
        ResourceBundle bundle = getBundle(language.getLocale());
        try {
            String s = bundle.getString(key);
            return MessageFormat.format(s, args);
        } catch (Exception ex) {
            return def;
        }
    }


    public static StringBinding getBinding(String key, Object... args) {
        return Bindings.createStringBinding(() -> getOrDefault(key, args), locale);
    }

    public static StringBinding getBinding(Callable<String> callable) {
        return Bindings.createStringBinding(callable, locale);
    }

    public static ResourceBundle getBundle(Locale locale) {
        return ResourceBundle.getBundle(getBundleBaseName(), locale);
    }


    public static Locale getLocale() {
        return locale.get();
    }


    public static ObjectProperty<Locale> localeProperty() {
        return locale;
    }

    public static void setLanguage(Language language) {
        locale.set(language.getLocale());
    }


    public static Language[] getSupportedLanguages() {
        return Language.values();
    }


    public static String getBundleBaseName() {
        return "com.example.desktop.i18n.locale";
    }
}
