/*
 * Copyright 2017-2018 Ping Identity Corporation
 * All Rights Reserved.
 */
/*
 * Copyright (C) 2017-2018 Ping Identity Corporation
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
package com.unboundid.util.ssl.cert;



import java.util.Arrays;
import java.util.EnumSet;

import org.testng.annotations.Test;

import com.unboundid.asn1.ASN1BitString;
import com.unboundid.ldap.sdk.LDAPSDKTestCase;



/**
 * This class provides test coverage for the
 * CRLDistributionPointRevocationReason class.
 */
public class CRLDistributionPointRevocationReasonTestCase
       extends LDAPSDKTestCase
{
  /**
   * Performs a number of tests for the defined set of values.
   *
   * @throws  Exception  If an unexpected problem occurs.
   */
  @Test()
  public void testValues()
         throws Exception
  {
    for (final CRLDistributionPointRevocationReason reason :
         CRLDistributionPointRevocationReason.values())
    {
      assertNotNull(reason);

      assertNotNull(reason.getName());

      final int bitPosition = reason.getBitPosition();
      final boolean[] minimumBits = new boolean[bitPosition+1];
      Arrays.fill(minimumBits, false);
      minimumBits[bitPosition] = true;
      assertNotNull(
           CRLDistributionPointRevocationReason.getReasonSet(
                new ASN1BitString(minimumBits)));
      assertEquals(
           CRLDistributionPointRevocationReason.getReasonSet(
                new ASN1BitString(minimumBits)),
           EnumSet.of(reason));

      final boolean[] moreBitsThanNecessary = new boolean[100];
      Arrays.fill(minimumBits, false);
      minimumBits[bitPosition] = true;
      assertNotNull(CRLDistributionPointRevocationReason.getReasonSet(
           new ASN1BitString(minimumBits)));
      assertEquals(
           CRLDistributionPointRevocationReason.getReasonSet(
                new ASN1BitString(minimumBits)),
           EnumSet.of(reason));

      assertNotNull(
           CRLDistributionPointRevocationReason.valueOf(reason.name()));
      assertEquals(CRLDistributionPointRevocationReason.valueOf(reason.name()),
           reason);
    }
  }



  /**
   * Tests the behavior when trying to decode a bit string with no bits set.
   *
   * @throws  Exception  If an unexpected problem occurs.
   */
  @Test()
  public void testEmptySet()
         throws Exception
  {
    final boolean[] noBits = new boolean[0];
    assertNotNull(
         CRLDistributionPointRevocationReason.getReasonSet(
              new ASN1BitString(noBits)));
    assertTrue(
         CRLDistributionPointRevocationReason.getReasonSet(
              new ASN1BitString(noBits)).isEmpty());

    final boolean[] correctNumberOfBits =
         new boolean[CRLDistributionPointRevocationReason.values().length];
    Arrays.fill(correctNumberOfBits, false);
    assertNotNull(
         CRLDistributionPointRevocationReason.getReasonSet(
              new ASN1BitString(correctNumberOfBits)));
    assertTrue(
         CRLDistributionPointRevocationReason.getReasonSet(
              new ASN1BitString(correctNumberOfBits)).isEmpty());

    final boolean[] moreBitsThanNecessary = new boolean[100];
    Arrays.fill(moreBitsThanNecessary, false);
    assertNotNull(
         CRLDistributionPointRevocationReason.getReasonSet(
              new ASN1BitString(moreBitsThanNecessary)));
    assertTrue(
         CRLDistributionPointRevocationReason.getReasonSet(
              new ASN1BitString(moreBitsThanNecessary)).isEmpty());
  }



  /**
   * Tests the behavior when trying to decode a bit string with all bits set.
   *
   * @throws  Exception  If an unexpected problem occurs.
   */
  @Test()
  public void testFullSet()
         throws Exception
  {
    final boolean[] correctNumberOfBits =
         new boolean[CRLDistributionPointRevocationReason.values().length];
    Arrays.fill(correctNumberOfBits, true);
    assertNotNull(
         CRLDistributionPointRevocationReason.getReasonSet(
              new ASN1BitString(correctNumberOfBits)));
    assertFalse(
         CRLDistributionPointRevocationReason.getReasonSet(
              new ASN1BitString(correctNumberOfBits)).isEmpty());
    assertEquals(
         CRLDistributionPointRevocationReason.getReasonSet(
              new ASN1BitString(correctNumberOfBits)),
         EnumSet.allOf(CRLDistributionPointRevocationReason.class));

    final boolean[] moreBitsThanNecessary = new boolean[100];
    Arrays.fill(moreBitsThanNecessary, true);
    assertNotNull(
         CRLDistributionPointRevocationReason.getReasonSet(
              new ASN1BitString(moreBitsThanNecessary)));
    assertFalse(
         CRLDistributionPointRevocationReason.getReasonSet(
              new ASN1BitString(moreBitsThanNecessary)).isEmpty());
    assertEquals(
         CRLDistributionPointRevocationReason.getReasonSet(
              new ASN1BitString(moreBitsThanNecessary)),
         EnumSet.allOf(CRLDistributionPointRevocationReason.class));
  }



  /**
   * Tests the behavior with a nonexistent value.
   *
   * @throws  Exception  If an unexpected problem occurs.
   */
  @Test()
  public void testNonexistentValue()
         throws Exception
  {
    try
    {
      CRLDistributionPointRevocationReason.valueOf("nonexistent");
      fail("Expected an exception for a nonexistent value");
    }
    catch (final IllegalArgumentException iae)
    {
      // This was expected.
    }
  }



  /**
   * Tests the {@code toBitString} method with a number of values.
   *
   * @throws  Exception  If an unexpected problem occurs.
   */
  @Test()
  public void testToBitString()
         throws Exception
  {
    EnumSet<CRLDistributionPointRevocationReason> set =
         EnumSet.noneOf(CRLDistributionPointRevocationReason.class);
    assertEquals(
         CRLDistributionPointRevocationReason.getReasonSet(
              CRLDistributionPointRevocationReason.toBitString((byte) 0x80,
                   set)),
         set);

    set = EnumSet.allOf(CRLDistributionPointRevocationReason.class);
    assertEquals(
         CRLDistributionPointRevocationReason.getReasonSet(
              CRLDistributionPointRevocationReason.toBitString((byte) 0x80,
                   set)),
         set);

    set = EnumSet.of(CRLDistributionPointRevocationReason.SUPERSEDED);
    assertEquals(
         CRLDistributionPointRevocationReason.getReasonSet(
              CRLDistributionPointRevocationReason.toBitString((byte) 0x80,
                   set)),
         set);
  }
}
