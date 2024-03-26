package com.ragerobotics.robot2024.systems;

import java.util.Optional;

import com.ragerobotics.robot2024.Constants;
import com.ragerobotics.robot2024.Robot.Mode;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;

public class LEDs implements ISystem {
    private static LEDs instance;

    public static LEDs getInstance() {
        if (instance == null) {
            instance = new LEDs();
        }

        return instance;
    }

    static AddressableLED m_led = new AddressableLED(Constants.kLEDChannel);
    static AddressableLEDBuffer m_ledBuffer = new AddressableLEDBuffer(600);

    static int m_PixelHue;

    static Optional<Alliance> alliance = DriverStation.getAlliance();

    public LEDs() {
        m_led.setLength(m_ledBuffer.getLength());
        m_led.setData(m_ledBuffer);
        m_led.start();
    }

    public static void allianceColor() {

        if (alliance.isPresent() && alliance.get() == Alliance.Red) {

            for (var i = 0; i < m_ledBuffer.getLength(); i++) {
                m_ledBuffer.setRGB(i, 255, 0, 0);
            }
        }

        else {
            for (var i = 0; i < m_ledBuffer.getLength(); i++) {
                m_ledBuffer.setRGB(i, 0, 0, 255);
            }
        }

        m_led.setData(m_ledBuffer);

    }

    public static void rainbowColors() {
        for (var i = 0; i < m_ledBuffer.getLength(); i++) {
            final var hue = (m_PixelHue + (i * 180 / m_ledBuffer.getLength())) % 180;

            m_ledBuffer.setHSV(i, hue, 255, 128);
        }

        m_PixelHue += 3;

        m_PixelHue %= 180;

        m_led.setData(m_ledBuffer);

    }

    public static void fancyAllianceColor() {
        if (alliance.isPresent() && alliance.get() == Alliance.Red) {

            for (var i = 0; i < m_ledBuffer.getLength(); i++) {
                final var hue = (m_PixelHue + (i * 180 / m_ledBuffer.getLength()));

                m_ledBuffer.setHSV(i, hue, 255, 128);
            }

            if (m_PixelHue > 20) {
                m_PixelHue = 0;
            }
            else {
                m_PixelHue += 1;
            }
        }

        else {
            for (var i = 0; i < m_ledBuffer.getLength(); i++) {
                final var hue = (m_PixelHue + (i * 180 / m_ledBuffer.getLength()));

                m_ledBuffer.setHSV(i, hue, 255, 128);
            }

            if (m_PixelHue > 135) {
                m_PixelHue = 90;
            }
            else {
                m_PixelHue += 1;
            }
        }

        m_led.setData(m_ledBuffer);

    }
    

    public static void whiteLights() {
        for (var i = 0; i < m_ledBuffer.getLength(); i++) {
            m_ledBuffer.setRGB(i, 255, 255, 255);
        }
    }

    @Override
    public void onUpdate(double timestamp, Mode mode) {

    }
}
