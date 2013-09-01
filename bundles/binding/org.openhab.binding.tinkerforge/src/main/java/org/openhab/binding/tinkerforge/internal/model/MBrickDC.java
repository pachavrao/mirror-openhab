/**
 * 
 *  openHAB, the open Home Automation Bus.
 *  Copyright (C)  2013, Thomas Weiss <theo.weiss@gmail.com>
 * 
 *  See the contributors.txt file in the distribution for a
 *  full listing of individual contributors.
 * 
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as
 *  published by the Free Software Foundation; either version 3 of the
 *  License, or (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 *  GNU General Public License for more details.
 * 
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, see <http://www.gnu.org/licenses>.
 * 
 *  Additional permission under GNU GPL version 3 section 7
 * 
 *  If you modify this Program, or any covered work, by linking or
 *  combining it with Eclipse (or a modified version of that library),
 *  containing parts covered by the terms of the Eclipse Public License
 *  (EPL), the licensors of this Program grant you additional permission
 *  to convey the resulting work.
 * 
 */
package org.openhab.binding.tinkerforge.internal.model;

import com.tinkerforge.BrickDC;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>MBrick DC</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openhab.binding.tinkerforge.internal.model.MBrickDC#getDeviceType <em>Device Type</em>}</li>
 *   <li>{@link org.openhab.binding.tinkerforge.internal.model.MBrickDC#getVelocity <em>Velocity</em>}</li>
 *   <li>{@link org.openhab.binding.tinkerforge.internal.model.MBrickDC#getCurrentVelocity <em>Current Velocity</em>}</li>
 *   <li>{@link org.openhab.binding.tinkerforge.internal.model.MBrickDC#getAcceleration <em>Acceleration</em>}</li>
 *   <li>{@link org.openhab.binding.tinkerforge.internal.model.MBrickDC#getPwmFrequency <em>Pwm Frequency</em>}</li>
 *   <li>{@link org.openhab.binding.tinkerforge.internal.model.MBrickDC#getDriveMode <em>Drive Mode</em>}</li>
 *   <li>{@link org.openhab.binding.tinkerforge.internal.model.MBrickDC#getSwitchOnVelocity <em>Switch On Velocity</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openhab.binding.tinkerforge.internal.model.ModelPackage#getMBrickDC()
 * @model superTypes="org.openhab.binding.tinkerforge.internal.model.MInSwitchActor org.openhab.binding.tinkerforge.internal.model.MDevice<org.openhab.binding.tinkerforge.internal.model.MTinkerBrickDC> org.openhab.binding.tinkerforge.internal.model.MTFConfigConsumer<org.openhab.binding.tinkerforge.internal.model.TFBrickDCConfiguration>"
 * @generated
 */
public interface MBrickDC extends MInSwitchActor, MDevice<BrickDC>, MTFConfigConsumer<TFBrickDCConfiguration>
{
  /**
   * Returns the value of the '<em><b>Device Type</b></em>' attribute.
   * The default value is <code>"brick_dc"</code>.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Device Type</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Device Type</em>' attribute.
   * @see org.openhab.binding.tinkerforge.internal.model.ModelPackage#getMBrickDC_DeviceType()
   * @model default="brick_dc" unique="false" changeable="false"
   * @generated
   */
  String getDeviceType();

  /**
   * Returns the value of the '<em><b>Velocity</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Velocity</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Velocity</em>' attribute.
   * @see #setVelocity(short)
   * @see org.openhab.binding.tinkerforge.internal.model.ModelPackage#getMBrickDC_Velocity()
   * @model unique="false"
   * @generated
   */
  short getVelocity();

  /**
   * Sets the value of the '{@link org.openhab.binding.tinkerforge.internal.model.MBrickDC#getVelocity <em>Velocity</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Velocity</em>' attribute.
   * @see #getVelocity()
   * @generated
   */
  void setVelocity(short value);

  /**
   * Returns the value of the '<em><b>Current Velocity</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Current Velocity</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Current Velocity</em>' attribute.
   * @see #setCurrentVelocity(short)
   * @see org.openhab.binding.tinkerforge.internal.model.ModelPackage#getMBrickDC_CurrentVelocity()
   * @model unique="false"
   * @generated
   */
  short getCurrentVelocity();

  /**
   * Sets the value of the '{@link org.openhab.binding.tinkerforge.internal.model.MBrickDC#getCurrentVelocity <em>Current Velocity</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Current Velocity</em>' attribute.
   * @see #getCurrentVelocity()
   * @generated
   */
  void setCurrentVelocity(short value);

  /**
   * Returns the value of the '<em><b>Acceleration</b></em>' attribute.
   * The default value is <code>"10000"</code>.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Acceleration</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Acceleration</em>' attribute.
   * @see #setAcceleration(int)
   * @see org.openhab.binding.tinkerforge.internal.model.ModelPackage#getMBrickDC_Acceleration()
   * @model default="10000" unique="false"
   * @generated
   */
  int getAcceleration();

  /**
   * Sets the value of the '{@link org.openhab.binding.tinkerforge.internal.model.MBrickDC#getAcceleration <em>Acceleration</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Acceleration</em>' attribute.
   * @see #getAcceleration()
   * @generated
   */
  void setAcceleration(int value);

  /**
   * Returns the value of the '<em><b>Pwm Frequency</b></em>' attribute.
   * The default value is <code>"15000"</code>.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Pwm Frequency</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Pwm Frequency</em>' attribute.
   * @see #setPwmFrequency(int)
   * @see org.openhab.binding.tinkerforge.internal.model.ModelPackage#getMBrickDC_PwmFrequency()
   * @model default="15000" unique="false"
   * @generated
   */
  int getPwmFrequency();

  /**
   * Sets the value of the '{@link org.openhab.binding.tinkerforge.internal.model.MBrickDC#getPwmFrequency <em>Pwm Frequency</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Pwm Frequency</em>' attribute.
   * @see #getPwmFrequency()
   * @generated
   */
  void setPwmFrequency(int value);

  /**
   * Returns the value of the '<em><b>Drive Mode</b></em>' attribute.
   * The default value is <code>"Break"</code>.
   * The literals are from the enumeration {@link org.openhab.binding.tinkerforge.internal.model.DCDriveMode}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Drive Mode</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Drive Mode</em>' attribute.
   * @see org.openhab.binding.tinkerforge.internal.model.DCDriveMode
   * @see #setDriveMode(DCDriveMode)
   * @see org.openhab.binding.tinkerforge.internal.model.ModelPackage#getMBrickDC_DriveMode()
   * @model default="Break" unique="false"
   * @generated
   */
  DCDriveMode getDriveMode();

  /**
   * Sets the value of the '{@link org.openhab.binding.tinkerforge.internal.model.MBrickDC#getDriveMode <em>Drive Mode</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Drive Mode</em>' attribute.
   * @see org.openhab.binding.tinkerforge.internal.model.DCDriveMode
   * @see #getDriveMode()
   * @generated
   */
  void setDriveMode(DCDriveMode value);

  /**
   * Returns the value of the '<em><b>Switch On Velocity</b></em>' attribute.
   * The default value is <code>"10000"</code>.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Switch On Velocity</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Switch On Velocity</em>' attribute.
   * @see #setSwitchOnVelocity(short)
   * @see org.openhab.binding.tinkerforge.internal.model.ModelPackage#getMBrickDC_SwitchOnVelocity()
   * @model default="10000" unique="false"
   * @generated
   */
  short getSwitchOnVelocity();

  /**
   * Sets the value of the '{@link org.openhab.binding.tinkerforge.internal.model.MBrickDC#getSwitchOnVelocity <em>Switch On Velocity</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Switch On Velocity</em>' attribute.
   * @see #getSwitchOnVelocity()
   * @generated
   */
  void setSwitchOnVelocity(short value);

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @model annotation="http://www.eclipse.org/emf/2002/GenModel body=''"
   * @generated
   */
  void init();

} // MBrickDC
