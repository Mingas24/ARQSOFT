FROM mcr.microsoft.com/dotnet/aspnet:7.0 AS base
WORKDIR /app
EXPOSE 80
EXPOSE 443

FROM mcr.microsoft.com/dotnet/sdk:7.0 AS build
WORKDIR /src
COPY ["Sandwich/Sandwich.csproj", "Sandwich/"]
RUN dotnet restore "Sandwich/Sandwich.csproj"
COPY . .
WORKDIR "/src/Sandwich"
RUN dotnet build "Sandwich.csproj" -c Release -o /app/build

FROM build AS publish
RUN dotnet publish "Sandwich.csproj" -c Release -o /app/publish

FROM base AS final
WORKDIR /app
COPY --from=publish /app/publish .
ENTRYPOINT ["dotnet", "Sandwich.dll"]
