import React, { useState } from "react";
import axios from "axios";

function Login({ setIsLoggedIn }) {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const handleSubmit = async (event) => {
    event.preventDefault();

    try {
      const response = await axios.post(
        "  http://localhost:8080/api/v1/users/login",
        { username, password }
      );
      if (response.status === 200) {
        localStorage.setItem("jwtToken", response.data);
        alert("Login successful");
      }
    } catch (error) {
      if (error.response && error.response.status === 401) {
        alert("Invalid username or password");
      } else {
        alert("An error occurred");
      }
    }
  };

  return (
    <>
      <form onSubmit={handleSubmit}>
        <label>
          Username:
          <input
            type="text"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
          />
        </label>
        <label>
          Password:
          <input
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
        </label>
        <input type="submit" value="Login" />
      </form>
      <button
        onClick={() => {
          localStorage.removeItem("jwtToken");
          setIsLoggedIn(false);
        }}
      >
        Logout
      </button>
    </>
  );
}

export default Login;
