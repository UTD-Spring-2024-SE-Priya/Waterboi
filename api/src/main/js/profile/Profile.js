import React, { useState, useEffect } from 'react';
import { Container, TextField, Button, Typography } from '@mui/material';

export default function Profile() {
  const [user, setUser] = useState({ goal: 0});
  useEffect(() => {
    fetch('/api/user/profile')
      .then(response => response.json())
      .then(data => setUser(data));
  }, []);

  const handleChange = (event) => {
    setUser({ ...user, [event.target.name]: event.target.value });
  };

  const handleSubmit = (event) => {
    event.preventDefault();

    fetch('/api/user/profile', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ goal: user.goal }),
    });
  };

  return (
    <Container sx={{maxWidth: 'sm'}}>
      <Typography variant="h4" align="center">User Profile</Typography>
      <form noValidate autoComplete="off" onSubmit={handleSubmit}>
        <TextField
          name="goal"
          label="Daily Goal"
          value={user.goal}
          onChange={handleChange}
          fullWidth
          margin="normal"
        />
        <Button variant="contained" color="primary" fullWidth type="submit">
          Save
        </Button>
      </form>
    </Container>
  );
}
