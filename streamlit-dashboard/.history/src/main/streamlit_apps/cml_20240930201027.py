import time
import streamlit as st
from PIL import Image
import base64

# Set page configuration
st.set_page_config(page_title="Breathing and Mindfulness", layout="wide")

# Add logo


# Add background image
pg_bg_image = """
    <style>
    [data-testid="stAppViewContainer"]{
    background-image: url("https://www.pixelstalk.net/wp-content/uploads/2016/09/Best-Beautiful-Images-For-Desktop-Nature.png");
    background-size: cover;
    }
    </style>
"""

# Add title
st.title("Breathing and Mindfulness")

# Add calming nature sounds
audio_file = "nature.mp3"
st.audio(audio_file, format="audio/mp3", start_time=0, loop=True, autoplay=True)

# Play audio automatically
st.markdown(
    """
    <script>
        // Play the audio automatically when the app is loaded
        audio.play();
    </script>
    <style>
    audio {
        display: none;
    }
    </style>
    """,
    unsafe_allow_html=True
)

# Hide audio player from view
st.markdown(
    """
    <style>
    audio {{
        display: none;
    }}
    </style>
    """,
    unsafe_allow_html=True
)

# Rest of the code...
# Breathing exercises
st.subheader("Breathing Exercises:")
st.write("Take slow, deep breaths in through your nose and out through your mouth.")
st.write("Inhale for 4 seconds, hold for 4 seconds, exhale for 4 seconds, hold for 4 seconds.")

# Guided meditation
st.subheader("Guided Meditation:")
st.write("Close your eyes and focus on your breath.")
st.write("Imagine yourself in a peaceful, natural environment.")
st.write("Notice the sensations in your body and let go of any tension.")

# Timer for breathing exercises
st.subheader("Breathing Exercise Timer:")
seconds = st.slider("Select the duration of your breathing exercise (in seconds):", 30, 300, 60)

if st.button("Start Breathing Exercise"):
    st.write("Starting breathing exercise...")
    for i in range(seconds):
        st.write(f"Breathing... ({i+1}/{seconds})")
        time.sleep(1)
    st.write("Breathing exercise complete.")

# Mindfulness quotes
st.subheader("Mindfulness Quotes:")
st.write("The present moment is the only moment available to us, and it is the door to all moments.")
st.write("Mindfulness is the practice of being present in the moment, without judgment or distraction.")

# Call to action
st.subheader("Take a Moment to Breathe and Reflect:")
st.write("Take a few deep breaths and focus on the present moment.")
st.write("Notice your thoughts and emotions, and let go of any tension or stress.")