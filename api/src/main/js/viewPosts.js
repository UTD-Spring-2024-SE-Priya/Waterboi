import React, { useState, useEffect } from 'react';

function ViewPosts() {
    const [posts, setPosts] = useState([]);
    const [units, setUnits] = useState({});

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
            console.log(unitsMap);
        })
        .catch(error => console.error('Error fetching units:', error));
    }, []);

    return (
        <div className="App">
            <table>
                <thead>
                    <tr>
                        <th>appuserId</th>
                        <th>quantity</th>
                        <th>unit</th>
                        <th>postTime</th>
                    </tr>
                </thead>
                <tbody>
                    {posts.map(post => (
                        <tr key={post.id}>
                            <td>{post.appuserId}</td>
                            <td>{units[post.unitId]?.name || 'Loading...'}</td>
                            <td>{post.quantity / units[post.unitId]?.literMultiple || 'Loading...'}</td>
                            <td>{(new Date(post.postTime)).toString()}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
}
export default ViewPosts;