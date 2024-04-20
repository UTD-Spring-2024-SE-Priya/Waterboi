
import Link from '@mui/material/Link';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Title from './Title';
import React, { useState, useEffect } from 'react';



function preventDefault(event) {
  event.preventDefault();
}

export default function Orders() {
    const [posts, setPosts] = useState([]);
    const [units, setUnits] = useState({});
    const [showSocialMedia, setShowSocialMedia] = useState(false);
    const handleSocialMediaClick = () => {
        setShowSocialMedia(!showSocialMedia);
    };

    useEffect(() => {
        fetch('/api/post/all')
        .then(response => response.json())
        .then(data => setPosts(data))
        .catch(error => console.error('Error fetching data:', error));


        fetch('/api/post/units')
        .then(response => response.json())
        .then(data => {
            const unitsMap = data.reduce((acc, unit) => {
                acc[unit.id] = {
                    name: unit.name,
                    literMultiple: unit.literMultiple
                }
                return acc;
            }, {});
            setUnits(unitsMap);
        })
        .catch(error => console.error('Error fetching units:', error));
    }, []);

  return (
    <React.Fragment>
      <Title>Recent Drinks</Title>
      <Table size="small">
        <TableHead>
          <TableRow>
            <TableCell>Date</TableCell>
            <TableCell>Time</TableCell>
            <TableCell>Liters</TableCell>

          </TableRow>
        </TableHead>
        <TableBody>
          {posts.map((post) => (
            <TableRow key={post.id}>
              <TableCell>{new Date(post.postTime).toLocaleDateString()}</TableCell>
              <TableCell>{new Date(post.postTime).toLocaleTimeString()}</TableCell>
              <TableCell>{post.quantity}</TableCell>
                <TableCell>
                    <Link href="#" onClick={handleSocialMediaClick}>
                        Social Media
                    </Link>
                    {showSocialMedia && (
                        <div style={{ marginLeft: '10px' }}>
                            <Link href="#" onClick={preventDefault} style={{ display: 'block' }}>
                                Facebook
                            </Link>
                            <Link href="#" onClick={preventDefault} style={{ display: 'block' }}>
                                Instagram
                            </Link>
                            <Link href="#" onClick={preventDefault} style={{ display: 'block' }}>
                                X
                            </Link>
                        </div>
                    )}
                </TableCell>

            </TableRow>
          ))}
        </TableBody>
      </Table>

    </React.Fragment>
  );
}