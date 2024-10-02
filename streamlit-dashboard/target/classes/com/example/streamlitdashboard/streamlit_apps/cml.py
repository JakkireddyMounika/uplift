import time
import streamlit as st
from PIL import Image
import base64

# Set page configuration
st.set_page_config(page_title="Breathing and Mindfulness", layout="wide",initial_sidebar_state="collapsed")

# Add background image
pg_bg_image = """
    <style>
    [data-testid="stAppViewContainer"]{
    background-image: linear-gradient(rgba(0,0,0,0.5),rgba(0,0,0,0.5)),url("https://wallpaperaccess.com/full/1276429.jpg");
    background-size: cover;
    }
    </style>
"""
st.markdown(pg_bg_image, unsafe_allow_html=True)

# Add title
st.title("Uplift: Breathing and Mindfulness Assistant")

# Add calming nature sounds
video_url="https://youtu.be/2dljZWZQLFQ?si=sMLt2u-dtA5FQa6Z"
st.sidebar.header("Set Size of the video")
width = st.sidebar.slider(
    label="Width", min_value=0, max_value=20, value=40, format="%d%%"
)

width = max(width, 0.01)
side = max((100 - width) / 2, 0.01)

_, container, _ = st.columns([side, width, side])
container.video(data=video_url)

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