/*
 * Copyright (C) 2025 The MegaMek Team. All Rights Reserved.
 *
 * This file is part of MekHQ.
 *
 * MekHQ is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License (GPL),
 * version 3 or (at your option) any later version,
 * as published by the Free Software Foundation.
 *
 * MekHQ is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 * A copy of the GPL should have been included with this project;
 * if not, see <https://www.gnu.org/licenses/>.
 *
 * NOTICE: The MegaMek organization is a non-profit group of volunteers
 * creating free software for the BattleTech community.
 *
 * MechWarrior, BattleMech, `Mech and AeroTech are registered trademarks
 * of The Topps Company, Inc. All Rights Reserved.
 *
 * Catalyst Game Labs and the Catalyst Game Labs logo are trademarks of
 * InMediaRes Productions, LLC.
 */
package mekhq.campaign.randomEvents.personalities.enums;

import static mekhq.campaign.randomEvents.personalities.enums.Aggression.ASSERTIVE;
import static mekhq.campaign.randomEvents.personalities.enums.Aggression.DECISIVE;
import static mekhq.campaign.randomEvents.personalities.enums.Aggression.MAXIMUM_VARIATIONS;
import static mekhq.campaign.randomEvents.personalities.enums.Aggression.NONE;
import static mekhq.utilities.MHQInternationalization.isResourceKeyValid;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import megamek.common.enums.Gender;
import mekhq.campaign.Campaign;
import mekhq.campaign.personnel.Person;
import mekhq.campaign.universe.Faction;
import org.junit.jupiter.api.Test;

public class AggressionTest {
    @Test
    public void testFromString_ValidStatus() {
        Aggression status = Aggression.fromString(DECISIVE.name());
        assertEquals(DECISIVE, status);
    }

    @Test
    public void testFromString_InvalidStatus() {
        Aggression status = Aggression.fromString("INVALID_STATUS");

        assertEquals(NONE, status);
    }

    @Test
    public void testFromString_NullStatus() {
        Aggression status = Aggression.fromString(null);

        assertEquals(NONE, status);
    }

    @Test
    public void testFromString_EmptyString() {
        Aggression status = Aggression.fromString("");

        assertEquals(NONE, status);
    }

    @Test
    public void testFromString_FromOrdinal() {
        Aggression status = Aggression.fromString(ASSERTIVE.ordinal() + "");

        assertEquals(ASSERTIVE, status);
    }

    @Test
    public void testGetLabel_notInvalid() {
        for (Aggression status : Aggression.values()) {
            String label = status.getLabel();
            assertTrue(isResourceKeyValid(label));
        }
    }

    @Test
    public void testGetDescription_notInvalid() {
        Campaign campaign = mock(Campaign.class);
        Faction campaignFaction = mock(Faction.class);
        when(campaign.getFaction()).thenReturn(campaignFaction);
        when(campaignFaction.getShortName()).thenReturn("MERC");
        Person person = new Person(campaign);

        for (Aggression trait : Aggression.values()) {
            for (int i = 0; i < MAXIMUM_VARIATIONS; i++) {
                person.setAggressionDescriptionIndex(i);
                String description = trait.getDescription(i, Gender.MALE, "Barry");
                assertTrue(isResourceKeyValid(description));
            }
        }
    }

    @Test
    public void testGetDescription_InvalidDescriptionIndex() {
        String description = NONE.getDescription(MAXIMUM_VARIATIONS, Gender.MALE, "Barry");
        assertTrue(isResourceKeyValid(description));
    }

    @Test
    public void testGetRoninMessage_notInvalid() {
        for (Aggression trait : Aggression.values()) {
            for (int i = 0; i < MAXIMUM_VARIATIONS; i++) {
                String description = trait.getRoninMessage("Commander");
                assertTrue(isResourceKeyValid(description));
            }
        }
    }
}
