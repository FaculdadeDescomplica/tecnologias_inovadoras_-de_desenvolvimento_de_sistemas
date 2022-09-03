import { useEffect, useState } from "react";
import SpringApiUrl from '../api/SpringApiUrl';

export const useFetch = (rota) => {
  const [data, setData] = useState(null);

  const [config, setConfig] = useState(null);
  const [method, setMethod] = useState(null);
  const [callFetch, setCallFetch] = useState(false);

  const [loading, setLoading] = useState(false);

  const [error, setError] = useState(false);

  const [itemId, setItemId] = useState(null);

  const httpConfig = (data, method) => {
    if (method === "POST") {
      setConfig({
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(data),
      });

      setMethod("POST");
    } else if (method === "PUT") {
      setConfig({
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(data),
      });

      setMethod("PUT");
      setItemId(data);
    } else if (method === "DELETE") {
      setConfig({
        method: "DELETE",
        headers: {
          "Content-Type": "application/json",
        },
      });

      setMethod("DELETE");
      setItemId(data);
    }
  };

  useEffect(() => {
    const fetchData = async () => {
      setLoading(true);

      try {
        const res = await fetch(SpringApiUrl(rota));

        const json = await res.json();

        setData(json);

        setMethod(null);

        setError(null);
      } catch (error) {
        console.log(error.message);

        setError("Houve um erro ao carregar os dados!");
      }

      setLoading(false);
    };

    fetchData();
  }, [rota, callFetch]);

  useEffect(() => {
    const httpRequest = async () => {
      if (method === "POST") {
        setLoading(true);

        let fetchOptions = [SpringApiUrl(rota), config];

        const res = await fetch(...fetchOptions);

        const json = await res.json();

        setCallFetch(json);
      } else if (method === "PUT") {
        setLoading(true);

        const updateUrl = `${SpringApiUrl(rota)}/${itemId}`;

        let fetchOptions = [updateUrl, config];

        const res = await fetch(...fetchOptions);

        const json = await res.json();

        setCallFetch(json);
      } else if (method === "DELETE") {
        const deleteUrl = `${SpringApiUrl(rota)}/${itemId}`;

        const res = await fetch(deleteUrl, config);

        const json = await res.status;

        setCallFetch(json);
      }
    };

    httpRequest();
  }, [config]);

  console.log(config);

  return { data, httpConfig, loading, error };
};
