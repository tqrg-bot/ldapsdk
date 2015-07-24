/*
 * Copyright 2014-2015 UnboundID Corp.
 * All Rights Reserved.
 */
/*
 * Copyright (C) 2015 UnboundID Corp.
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
package com.unboundid.ldap.sdk.unboundidds.extensions;



import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.unboundid.asn1.ASN1Element;
import com.unboundid.asn1.ASN1OctetString;
import com.unboundid.asn1.ASN1Sequence;
import com.unboundid.ldap.sdk.Control;
import com.unboundid.ldap.sdk.ExtendedRequest;
import com.unboundid.ldap.sdk.LDAPException;
import com.unboundid.ldap.sdk.ResultCode;
import com.unboundid.util.Debug;
import com.unboundid.util.NotMutable;
import com.unboundid.util.StaticUtils;
import com.unboundid.util.ThreadSafety;
import com.unboundid.util.ThreadSafetyLevel;
import com.unboundid.util.Validator;

import static com.unboundid.ldap.sdk.unboundidds.extensions.ExtOpMessages.*;



/**
 * <BLOCKQUOTE>
 *   <B>NOTE:</B>  This class is part of the Commercial Edition of the UnboundID
 *   LDAP SDK for Java.  It is not available for use in applications that
 *   include only the Standard Edition of the LDAP SDK, and is not supported for
 *   use in conjunction with non-UnboundID products.
 * </BLOCKQUOTE>
 * This class provides an extended request that may be used to create or update
 * a notification subscription.  The request has an OID of
 * 1.3.6.1.4.1.30221.2.6.38 and a value with the following encoding:
 * <BR><BR>
 * <PRE>
 *   SetNotificationSubscriptionRequest ::= SEQUENCE {
 *        notificationManagerID          OCTET STRING,
 *        notificationDestinationID      OCTET STRING,
 *        notificationSubscriptionID     OCTET STRING,
 *        subscriptionDetails            SEQUENCE OF OCTET STRING }
 * </PRE>
 */
@NotMutable()
@ThreadSafety(level=ThreadSafetyLevel.COMPLETELY_THREADSAFE)
public final class SetNotificationSubscriptionExtendedRequest
       extends ExtendedRequest
{
  /**
   * The OID (1.3.6.1.4.1.30221.2.6.38) for the set notification subscription
   * extended request.
   */
  public static final String SET_NOTIFICATION_SUBSCRIPTION_REQUEST_OID =
       "1.3.6.1.4.1.30221.2.6.38";



  /**
   * The serial version UID for this serializable class.
   */
  private static final long serialVersionUID = -5822283773149091097L;



  // The implementation-specific details for the notification subscription.
  private final List<ASN1OctetString> subscriptionDetails;

  // The notification destination ID.
  private final String destinationID;

  // The notification manager ID.
  private final String managerID;

  // The notification subscription ID.
  private final String subscriptionID;



  /**
   * Creates a new set notification subscription extended request with the
   * provided information.
   *
   * @param  managerID            The notification manager ID.  It must not be
   *                              {@code null}.
   * @param  destinationID        The notification destination ID.  It must not
   *                              be {@code null}.
   * @param  subscriptionID       The notification subscription ID.  It must not
   *                              be {@code null}.
   * @param  subscriptionDetails  The implementation-specific details for the
   *                              notification subscription.  At least one
   *                              detail value must be provided.
   */
  public SetNotificationSubscriptionExtendedRequest(final String managerID,
              final String destinationID, final String subscriptionID,
              final ASN1OctetString... subscriptionDetails)
  {
    this(managerID, destinationID, subscriptionID,
         StaticUtils.toList(subscriptionDetails));
  }



  /**
   * Creates a new set notification subscription extended request with the
   * provided information.
   *
   * Creates a new set notification subscription extended request with the
   * provided information.
   *
   * @param  managerID            The notification manager ID.  It must not be
   *                              {@code null}.
   * @param  destinationID        The notification destination ID.  It must not
   *                              be {@code null}.
   * @param  subscriptionID       The notification subscription ID.  It must not
   *                              be {@code null}.
   * @param  subscriptionDetails  The implementation-specific details for the
   *                              notification subscription.  At least one
   *                              detail value must be provided.
   * @param  controls             The set of controls to include in the request.
   *                              It may be {@code null} or empty if no controls
   *                              are needed.
   */
  public SetNotificationSubscriptionExtendedRequest(final String managerID,
              final String destinationID, final String subscriptionID,
              final Collection<ASN1OctetString> subscriptionDetails,
              final Control... controls)
  {
    super(SET_NOTIFICATION_SUBSCRIPTION_REQUEST_OID,
         encodeValue(managerID, destinationID, subscriptionID,
              subscriptionDetails),
         controls);

    this.managerID = managerID;
    this.destinationID = destinationID;
    this.subscriptionID = subscriptionID;
    this.subscriptionDetails = Collections.unmodifiableList(
         new ArrayList<ASN1OctetString>(subscriptionDetails));
  }



  /**
   * Creates a new set notification subscription extended request from the
   * provided generic extended request.
   *
   * @param  extendedRequest  The generic extended request to use to create this
   *                          set notification subscription extended request.
   *
   * @throws  LDAPException  If a problem occurs while decoding the request.
   */
  public SetNotificationSubscriptionExtendedRequest(
              final ExtendedRequest extendedRequest)
         throws LDAPException
  {
    super(extendedRequest);

    final ASN1OctetString value = extendedRequest.getValue();
    if (value == null)
    {
      throw new LDAPException(ResultCode.DECODING_ERROR,
           ERR_SET_NOTIFICATION_SUB_REQ_DECODE_NO_VALUE.get());
    }

    try
    {
      final ASN1Element[] elements =
           ASN1Sequence.decodeAsSequence(value.getValue()).elements();
      managerID =
           ASN1OctetString.decodeAsOctetString(elements[0]).stringValue();
      destinationID =
           ASN1OctetString.decodeAsOctetString(elements[1]).stringValue();
      subscriptionID =
           ASN1OctetString.decodeAsOctetString(elements[2]).stringValue();

      final ASN1Element[] detailElements =
           ASN1Sequence.decodeAsSequence(elements[3]).elements();
      final ArrayList<ASN1OctetString> detailList =
           new ArrayList<ASN1OctetString>(detailElements.length);
      for (final ASN1Element e : detailElements)
      {
        detailList.add(ASN1OctetString.decodeAsOctetString(e));
      }
      subscriptionDetails = Collections.unmodifiableList(detailList);
    }
    catch (final Exception e)
    {
      Debug.debugException(e);
      throw new LDAPException(ResultCode.DECODING_ERROR,
           ERR_SET_NOTIFICATION_SUB_REQ_ERROR_DECODING_VALUE.get(
                StaticUtils.getExceptionMessage(e)),
           e);
    }
  }



  /**
   * Encodes the provided information into an ASN.1 octet string suitable for
   * use as the value of this extended request.
   *
   * @param  managerID            The notification manager ID.  It must not be
   *                              {@code null}.
   * @param  destinationID        The notification destination ID.  It must not
   *                              be {@code null}.
   * @param  subscriptionID       The notification subscription ID.  It must not
   *                              be {@code null}.
   * @param  subscriptionDetails  The implementation-specific details for the
   *                              notification subscription.  At least one
   *                              detail value must be provided.
   *
   * @return  The ASN.1 octet string containing the encoded value.
   */
  private static ASN1OctetString encodeValue(final String managerID,
                      final String destinationID, final String subscriptionID,
                      final Collection<ASN1OctetString> subscriptionDetails)
  {
    Validator.ensureNotNull(managerID);
    Validator.ensureNotNull(destinationID);
    Validator.ensureNotNull(subscriptionID);
    Validator.ensureNotNull(subscriptionDetails);
    Validator.ensureFalse(subscriptionDetails.isEmpty());

    final ASN1Sequence valueSequence = new ASN1Sequence(
         new ASN1OctetString(managerID),
         new ASN1OctetString(destinationID),
         new ASN1OctetString(subscriptionID),
         new ASN1Sequence(new ArrayList<ASN1Element>(subscriptionDetails)));
    return new ASN1OctetString(valueSequence.encode());
  }



  /**
   * Retrieves the notification manager ID.
   *
   * @return  The notification manager ID.
   */
  public String getManagerID()
  {
    return managerID;
  }



  /**
   * Retrieves the notification destination ID.
   *
   * @return  The notification destination ID.
   */
  public String getDestinationID()
  {
    return destinationID;
  }



  /**
   * Retrieves the notification subscription ID.
   *
   * @return  The notification subscription ID.
   */
  public String getSubscriptionID()
  {
    return subscriptionID;
  }



  /**
   * Retrieves the implementation-specific details for the notification
   * subscription.
   *
   * @return  The implementation-specific details for the notification
   *          subscription.
   */
  public List<ASN1OctetString> getSubscriptionDetails()
  {
    return subscriptionDetails;
  }



  /**
   * {@inheritDoc}
   */
  @Override()
  public SetNotificationSubscriptionExtendedRequest duplicate()
  {
    return duplicate(getControls());
  }



  /**
   * {@inheritDoc}
   */
  @Override()
  public SetNotificationSubscriptionExtendedRequest
              duplicate(final Control[] controls)
  {
    final SetNotificationSubscriptionExtendedRequest r =
         new SetNotificationSubscriptionExtendedRequest(managerID,
              destinationID, subscriptionID, subscriptionDetails, controls);
    r.setResponseTimeoutMillis(getResponseTimeoutMillis(null));
    return r;
  }



  /**
   * {@inheritDoc}
   */
  @Override()
  public String getExtendedRequestName()
  {
    return INFO_EXTENDED_REQUEST_NAME_SET_NOTIFICATION_SUB.get();
  }



  /**
   * {@inheritDoc}
   */
  @Override()
  public void toString(final StringBuilder buffer)
  {
    buffer.append("SetNotificationSubscriptionExtendedRequest(managerID='");
    buffer.append(managerID);
    buffer.append("', destinationID='");
    buffer.append(destinationID);
    buffer.append("', subscriptionID='");
    buffer.append(subscriptionID);
    buffer.append("', subscriptionDetails=ASN1OctetString[");
    buffer.append(subscriptionDetails.size());
    buffer.append(']');

    final Control[] controls = getControls();
    if (controls.length > 0)
    {
      buffer.append(", controls={");
      for (int i=0; i < controls.length; i++)
      {
        if (i > 0)
        {
          buffer.append(", ");
        }

        buffer.append(controls[i]);
      }
      buffer.append('}');
    }

    buffer.append(')');
  }
}