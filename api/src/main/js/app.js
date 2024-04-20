import React, { useEffect } from 'react';
import Dashboard from './dashboard/dashboard.js';
import { BrowserRouter as Router, Route, useNavigate, Routes} from 'react-router-dom';

function Logout() {
    let navigate = useNavigate();
    useEffect(() => {
        fetch('/logout')
        .then(response => {
            if(response.ok) {
                window.location.href = '/login';
            }
        });
    });
    return null;
}
export default function App() {
    return (
        <Router>
            <Routes>
                <Route path="/logout" element={<Logout />} />
                <Route path="/home" element={<Dashboard />} />
            </Routes>
            
        </Router>
    );
}
