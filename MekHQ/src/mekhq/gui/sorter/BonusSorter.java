/*
 * Copyright (c) 2024 - The MegaMek Team. All Rights Reserved.
 *
 * This file is part of MekHQ.
 *
 * MegaMek is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MegaMek is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MegaMek. If not, see <http://www.gnu.org/licenses/>.
 */
package mekhq.gui.sorter;

import java.util.Comparator;

import megamek.logging.MMLogger;

/**
 * A comparator for bonuses written as strings with "-" sorted to the bottom
 * always
 *
 * @author Jay Lawson
 */
public class BonusSorter implements Comparator<String> {
    private static final MMLogger logger = MMLogger.create(BonusSorter.class);

    @Override
    public int compare(String s0, String s1) {
        int i0, i1;

        if (s0.contains("/")) {
            String[] temp = s0.split("/");
            if (temp[0].contains("-") && temp[1].contains("-")) {
                i0 = 99;
            } else {
                int t0;
                try {
                    t0 = temp[0].contains("-") ? 0 : Integer.parseInt(temp[0]);
                } catch (Exception e) {
                    logger.error("", e);
                    t0 = 0;
                }

                int t1;
                try {
                    t1 = temp[1].contains("-") ? 0 : Integer.parseInt(temp[1]);
                } catch (Exception e) {
                    logger.error("", e);
                    t1 = 0;
                }
                i0 = t0 + t1;
            }
        } else {
            try {
                i0 = s0.equals("-") ? 90 : Integer.parseInt(s0);
            } catch (Exception e) {
                logger.error("", e);
                i0 = 90;
            }
        }

        if (s1.contains("/")) {
            String[] temp = s1.split("/");
            if (temp[0].contains("-") && temp[1].contains("-")) {
                i1 = 99;
            } else {
                int t0;
                try {
                    t0 = temp[0].contains("-") ? 0 : Integer.parseInt(temp[0]);
                } catch (Exception e) {
                    logger.error("", e);
                    t0 = 0;
                }

                int t1;
                try {
                    t1 = temp[1].contains("-") ? 0 : Integer.parseInt(temp[1]);
                } catch (Exception e) {
                    logger.error("", e);
                    t1 = 0;
                }
                i1 = t0 + t1;
            }
        } else {
            try {
                i1 = s1.equals("-") ? 90 : Integer.parseInt(s1);
            } catch (Exception e) {
                logger.error("", e);
                i1 = 90;
            }
        }

        return Integer.compare(i0, i1);
    }
}
