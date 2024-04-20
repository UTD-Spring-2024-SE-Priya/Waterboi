import * as React from 'react';
import { PieChart } from '@mui/x-charts/PieChart';
import { Box } from '@mui/material';
import Typography from '@mui/material/Typography';

export default function TwoLevelPieChart() {
    const [drankToday, setDrankToday] = React.useState([]);
    const [drankWeek, setDrankWeek] = React.useState([]);
    const [dailyGoal, setDailyGoal] = React.useState([]);
    const [weeklyGoal, setWeeklyGoal] = React.useState([]);

    React.useEffect(() => {
        fetch('/api/post/sum/day')
        .then(response => response.json())
        .then(data => setDrankToday(data));
    }, []);

    React.useEffect(() => {
        fetch('/api/goal/day')
        .then(response => response.json())
        .then(data => setDailyGoal([
            { label: 'Drank Today', value: drankToday },
            { label: 'To Daily Goal', value: data - drankToday},
        ]));
    },[drankToday]);

    React.useEffect(() => {
        fetch('/api/post/sum/week')
        .then(response => response.json())
        .then(data => setDrankWeek(data));
    }, []);

    React.useEffect(() => {
        fetch('/api/goal/week')
        .then(response => response.json())
        .then(data => setWeeklyGoal([
            { label: 'Drank This Week', value: drankWeek },
            { label: 'To Weekly Goal', value: data - drankWeek},
        ]));
    },[drankWeek]);

  return (
    <Box display={'flex'} flexDirection={'column'} alignItems={'center'}>
        <Typography variant="h4" align="center">Progress</Typography>
        <Box display={'flex'} justifyContent={'center'}>
            <PieChart
                series={[
                    {
                    innerRadius: 60,
                    outerRadius: 80,
                    data: dailyGoal,
                    },
                    {
                    innerRadius: 100,
                    outerRadius: 120,
                    data: weeklyGoal,
                    },
                ]}
            width={400}
            height={300}
            slotProps={{
                legend: { hidden: true },
            }}
            />
        </Box>
    </Box>
  );
}