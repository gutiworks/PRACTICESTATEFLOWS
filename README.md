# STATEFLOW

```bash
private fun <T, S> fetchData(
        stateFlow: MutableStateFlow<S>,
        fetch: suspend () -> T,
        onSuccess: (T) -> S,
        onError: (String) -> S
    ) {
        viewModelScope.launch {
            try {
                val result = fetch()
                stateFlow.update { onSuccess(result) }
            } catch (e: Exception) {
                stateFlow.update { onError(e.message ?: "Unknown error") }
            }
        }
    }

    init {
        startData()
    }

    private fun startData(){
        fetchData(
            _uiState,
            repository::getPosts,
            onSuccess = { PostUiState.Success(it) },
            onError = { PostUiState.Error }
            )
    }
```
