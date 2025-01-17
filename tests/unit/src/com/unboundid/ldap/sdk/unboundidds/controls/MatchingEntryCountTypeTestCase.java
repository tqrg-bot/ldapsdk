/*
 * Copyright 2014-2018 Ping Identity Corporation
 * All Rights Reserved.
 */
/*
 * Copyright (C) 2014-2018 Ping Identity Corporation
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License (GPLv2 only)
 * or the terms of the GNU Lesser General Public License (LGPLv2.1 only)
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, see <http://www.gnu.org/licenses>.
 */
package com.unboundid.ldap.sdk.unboundidds.controls;



import org.testng.annotations.Test;

import com.unboundid.ldap.sdk.LDAPSDKTestCase;



/**
 * This class provides a set of test cases for the matching entry count type
 * enum.
 */
public final class MatchingEntryCountTypeTestCase
       extends LDAPSDKTestCase
{
  /**
   * Provides test coverage for the enum methods.
   *
   * @throws  Exception  If an unexpected problem occurs.
   */
  @Test()
  public void testEnum()
         throws Exception
  {
    for (final MatchingEntryCountType t : MatchingEntryCountType.values())
    {
      assertEquals(MatchingEntryCountType.valueOf(t.getBERType()), t);

      assertEquals(MatchingEntryCountType.valueOf(t.name()), t);
    }

    assertNull(MatchingEntryCountType.valueOf((byte) 0x12));

    try
    {
      MatchingEntryCountType.valueOf("undefined");
      fail("Expected an exception for a valueOf call with an undefined name");
    }
    catch (final Exception e)
    {
      // This was expected.
    }
  }



  /**
   * Provides test coverage for the {@code isMoreSpecificThan} method.
   *
   * @throws  Exception  If an unexpected problem occurs.
   */
  @Test()
  public void testIsMoreSpecificThan()
         throws Exception
  {
    assertFalse(MatchingEntryCountType.EXAMINED_COUNT.isMoreSpecificThan(
         MatchingEntryCountType.EXAMINED_COUNT));
    assertTrue(MatchingEntryCountType.EXAMINED_COUNT.isMoreSpecificThan(
         MatchingEntryCountType.UNEXAMINED_COUNT));
    assertTrue(MatchingEntryCountType.EXAMINED_COUNT.isMoreSpecificThan(
         MatchingEntryCountType.UPPER_BOUND));
    assertTrue(MatchingEntryCountType.EXAMINED_COUNT.isMoreSpecificThan(
         MatchingEntryCountType.UNKNOWN));

    assertFalse(MatchingEntryCountType.UNEXAMINED_COUNT.isMoreSpecificThan(
         MatchingEntryCountType.EXAMINED_COUNT));
    assertFalse(MatchingEntryCountType.UNEXAMINED_COUNT.isMoreSpecificThan(
         MatchingEntryCountType.UNEXAMINED_COUNT));
    assertTrue(MatchingEntryCountType.UNEXAMINED_COUNT.isMoreSpecificThan(
         MatchingEntryCountType.UPPER_BOUND));
    assertTrue(MatchingEntryCountType.UNEXAMINED_COUNT.isMoreSpecificThan(
         MatchingEntryCountType.UNKNOWN));

    assertFalse(MatchingEntryCountType.UPPER_BOUND.isMoreSpecificThan(
         MatchingEntryCountType.EXAMINED_COUNT));
    assertFalse(MatchingEntryCountType.UPPER_BOUND.isMoreSpecificThan(
         MatchingEntryCountType.UNEXAMINED_COUNT));
    assertFalse(MatchingEntryCountType.UPPER_BOUND.isMoreSpecificThan(
         MatchingEntryCountType.UPPER_BOUND));
    assertTrue(MatchingEntryCountType.UPPER_BOUND.isMoreSpecificThan(
         MatchingEntryCountType.UNKNOWN));

    assertFalse(MatchingEntryCountType.UNKNOWN.isMoreSpecificThan(
         MatchingEntryCountType.EXAMINED_COUNT));
    assertFalse(MatchingEntryCountType.UNKNOWN.isMoreSpecificThan(
         MatchingEntryCountType.UNEXAMINED_COUNT));
    assertFalse(MatchingEntryCountType.UNKNOWN.isMoreSpecificThan(
         MatchingEntryCountType.UPPER_BOUND));
    assertFalse(MatchingEntryCountType.UNKNOWN.isMoreSpecificThan(
         MatchingEntryCountType.UNKNOWN));
  }



  /**
   * Provides test coverage for the {@code isLessSpecificThan} method.
   *
   * @throws  Exception  If an unexpected problem occurs.
   */
  @Test()
  public void testIsLessSpecificThan()
         throws Exception
  {
    assertFalse(MatchingEntryCountType.EXAMINED_COUNT.isLessSpecificThan(
         MatchingEntryCountType.EXAMINED_COUNT));
    assertFalse(MatchingEntryCountType.EXAMINED_COUNT.isLessSpecificThan(
         MatchingEntryCountType.UNEXAMINED_COUNT));
    assertFalse(MatchingEntryCountType.EXAMINED_COUNT.isLessSpecificThan(
         MatchingEntryCountType.UPPER_BOUND));
    assertFalse(MatchingEntryCountType.EXAMINED_COUNT.isLessSpecificThan(
         MatchingEntryCountType.UNKNOWN));

    assertTrue(MatchingEntryCountType.UNEXAMINED_COUNT.isLessSpecificThan(
         MatchingEntryCountType.EXAMINED_COUNT));
    assertFalse(MatchingEntryCountType.UNEXAMINED_COUNT.isLessSpecificThan(
         MatchingEntryCountType.UNEXAMINED_COUNT));
    assertFalse(MatchingEntryCountType.UNEXAMINED_COUNT.isLessSpecificThan(
         MatchingEntryCountType.UPPER_BOUND));
    assertFalse(MatchingEntryCountType.UNEXAMINED_COUNT.isLessSpecificThan(
         MatchingEntryCountType.UNKNOWN));

    assertTrue(MatchingEntryCountType.UPPER_BOUND.isLessSpecificThan(
         MatchingEntryCountType.EXAMINED_COUNT));
    assertTrue(MatchingEntryCountType.UPPER_BOUND.isLessSpecificThan(
         MatchingEntryCountType.UNEXAMINED_COUNT));
    assertFalse(MatchingEntryCountType.UPPER_BOUND.isLessSpecificThan(
         MatchingEntryCountType.UPPER_BOUND));
    assertFalse(MatchingEntryCountType.UPPER_BOUND.isLessSpecificThan(
         MatchingEntryCountType.UNKNOWN));

    assertTrue(MatchingEntryCountType.UNKNOWN.isLessSpecificThan(
         MatchingEntryCountType.EXAMINED_COUNT));
    assertTrue(MatchingEntryCountType.UNKNOWN.isLessSpecificThan(
         MatchingEntryCountType.UNEXAMINED_COUNT));
    assertTrue(MatchingEntryCountType.UNKNOWN.isLessSpecificThan(
         MatchingEntryCountType.UPPER_BOUND));
    assertFalse(MatchingEntryCountType.UNKNOWN.isLessSpecificThan(
         MatchingEntryCountType.UNKNOWN));
  }
}
