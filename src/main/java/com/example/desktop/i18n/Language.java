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

import java.util.Locale;


public enum Language {
    ARABIC(Locale.forLanguageTag("ar")),
    ENGLISH(Locale.ENGLISH),
    FRENCH(Locale.FRENCH),
    ITALIANO(Locale.ITALIAN),
    RUSSIAN(new Locale("ru", "RU"));

    private final Locale locale;

    Language(Locale locale) {
        this.locale = locale;
    }

    public static Language defaultLanguage() {
        return RUSSIAN;
    }

    public static Locale defaultLocale() {
        return RUSSIAN.getLocale();
    }

    public Locale getLocale() {
        return locale;
    }
}
