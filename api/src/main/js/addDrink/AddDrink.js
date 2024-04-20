import React, { useState } from 'react';
import styled from 'styled-components';
import { DashboardContext } from '../dashboard/DashboardContext';
import { useContext } from 'react';
import { Typography } from '@mui/material';



const Holder = styled.div`
  background-color: white;
  padding: 20px;
  font-family: Arial, sans-serif;
  text-align: center;
`;

const Title = styled.h1`
  color: black;
  margin-bottom: 20px;
`;

const Input = styled.input`
  padding: 10px;
  font-size: 16px;
  margin-bottom: 20px;
`;


const Question = styled.p`
  margin-bottom: 20px;
`;

const ButtonTwo = styled.button`
  background-color: blue;
  color: white;
  border: none;
  padding: 10px 20px;
  font-size: 16px;
  cursor: pointer;
`;

const AddDrink = () => {
  const { setCurrentView } = useContext(DashboardContext);
  const [value, setValue] = useState('2');

  const handleInputChange = (e) => {
    setValue(e.target.value);
  };

  const handleSubmit = (event) => {
  
    fetch('/api/post/new', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ 
        quantity: value,
      unitId : 1 }),
    });
    setCurrentView('drinks');
  };
  
  return (
    <Holder>
      <Title>Been Drinking?</Title>
      <Input
        type="text"
        value={value}
        onChange={handleInputChange}
      />
      <Typography variant="h6" align="center">Liters</Typography>
      <Question>Sure about that?</Question>
      <ButtonTwo onClick={handleSubmit} >I am Sure!</ButtonTwo>
    </Holder>
  );
};

export default AddDrink;