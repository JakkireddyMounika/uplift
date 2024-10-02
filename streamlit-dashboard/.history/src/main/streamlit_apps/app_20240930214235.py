import streamlit as st
import os
import google.generativeai as genai

# Set API key
# Get the API key from the environment variable
api_key = os.getenv('GOOGLE_API_KEY')


genai.configure(api_key=api_key)

# Load Gemini Pro model
model = genai.GenerativeModel('gemini-pro')

def main():
    # Page configuration
    st.set_page_config(page_title="Mental Health Chatbot", layout="wide")

    # Formatting for the header
    st.title("Uplift")

    pg_bg_image = """
    <style>
    [data-testid="stAppViewContainer"]{
    background-image: linear-gradient(rgba(0,0,0,0.5),rgba(0,0,0,0.5)),url("https://wallpaperaccess.com/full/5517441.jpg");
    background-size: cover;
    }
    </style>
    """
    st.markdown(pg_bg_image, unsafe_allow_html=True)
    

    # Conversation topic buttons
    st.write("Select a conversation topic:")
    col1, col2, col3, col4 = st.columns(4)
    with col1:
        anxiety_button = st.button("Anxiety and Stress 🤯")
    with col2:
        depression_button = st.button("Depression and Mood 😔")
    with col3:
        relationships_button = st.button("Relationships and Social 👫")
    with col4:
        general_button = st.button("General Conversation 💬")

    # Get input
    st.subheader("Type your message:")
    message = st.text_input("")

    st.subheader("Chatbot Response:")

    if anxiety_button or depression_button or relationships_button or general_button:
        try:
            # Determine the conversation topic
            if anxiety_button:
                topic = "Anxiety and Stress"
            elif depression_button:
                topic = "Depression and Mood"
            elif relationships_button:
                topic = "Relationships and Social"
            else:
                topic = "General Conversation"

            # Generate the response using the model
            response = model.generate_content(f"You are a mental health care chatbot. {topic}. {message}")

            # Extract the generated text (assuming response.text is the output format)
            generated_text = response.text

            if response.parts:
                st.write(generated_text)
                x = generated_text
            else:
                st.write("No response generated.")
        except Exception as e:
            st.error(f"Error: {str(e)}")

    # Display chat history
    st.subheader("Chat History:")
    if 'chat_history' not in st.session_state:
        st.session_state.chat_history = []
        st.session_state.uplift_history = []
    if message:
        st.session_state.chat_history.append(message)
    if 'x' in locals():
        st.session_state.uplift_history.append(x)
    for i, (message, uplift) in enumerate(zip(st.session_state.chat_history, st.session_state.uplift_history), start=1):
        st.write(f"{i}. You: {message}")
        st.write(f"{i}. Uplift: {uplift}")


if __name__ == "__main__":
    main()