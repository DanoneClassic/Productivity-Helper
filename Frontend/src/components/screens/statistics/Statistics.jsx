import React, { useEffect, useState } from 'react';

// Static statistics data, presumably could be fetched from an API
const statistics = [
    { name: "Total Users", value: 1000 },
    { name: "Active Users", value: 750 },
    { name: "Subscriptions", value: 300 },
    { name: "Issues Reported", value: 20 },
    { name: "Total Users", value: 1000 },
    { name: "Active Users", value: 750 },
    { name: "Subscriptions", value: 300 },
    { name: "Issues Reported", value: 20 }
];

const Loader = () => <div>Loading...</div>;

export function Statistics() {
    const [data, setData] = useState([]);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        setTimeout(() => {
            setData(statistics); // Simulate fetching data
            setLoading(false);   // Set loading to false after data is 'fetched'
        }, 1000);
    }, []);

    return loading ? <Loader /> : (
        <div className='grid grid-cols-4 gap-12 mt-7'>
            {data.length ? data.map(statistic => (
                <div key={statistic.name} className="bg-gray-800 rounded p-4 text-zinc-50 text-center hover:-translate-y-1 transition-transform duration-500">
                    <div className='text-xl'>{statistic.name}</div>
                    <div className='text-3xl'>{statistic.value}</div>
                </div>
            )) : <div>Statistics not loaded!</div>}
        </div>
    );
}